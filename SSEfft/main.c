#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <malloc.h>
#include <math.h>
#include <sys/mman.h>
#include "DFT/FFT.h"
#include "DFT/StaticFFT_10.h"
#include "DFT/StaticFFT_5.h"
#include "DFT/FTable.h"
#include <string.h>
#include <x86intrin.h>
#include <mmintrin.h>
#include <xmmintrin.h>

void DFT(float Real[], float OutputRe[], float OutputIm[], int Amount)
{
	int k, n;
	float SumRe, SumIm;
	for(k = 0;k < Amount;k ++)
	{
		SumRe = 0;
		SumIm = 0;
		for(n = 0;n < Amount;n ++)
		{
			SumRe += Real[n] * cos(2 * 3.1415926535 / Amount * n * k);
			SumIm -= Real[n] * sin(2 * 3.1415926535 / Amount * n * k);
		}
		OutputRe[k] = SumRe;
		OutputIm[k] = SumIm;
	}
}
int main()
{
	int i;
	int pagesize = sysconf(_SC_PAGE_SIZE);
	int pagenum = 1024 * 1024 / pagesize;
	void* CodeFFT_10 = memalign(pagesize, pagenum * pagesize);
	mprotect(CodeFFT_10, pagenum * pagesize, PROT_READ | PROT_WRITE | PROT_EXEC);
	
	float* TRe = _mm_malloc(1024 * 4, 16);
	float* TIm = _mm_malloc(1024 * 4, 16);
	float* FRe = _mm_malloc(1024 * 4, 16);
	float* FIm = _mm_malloc(1024 * 4, 16);

	for(i = 0;i < 1024;i ++)
	{
		TRe[i] = sin((float)i * 3.1415926 / 100);
		TIm[i] = 0;
	}

	unsigned short int* id;
	void (*FuncFFT_10_Orig)() = FFT_Static_10;
	void (*FuncFFT_10)() = CodeFFT_10;
	unsigned char* CodePtr;
	int size;

	i = 0;
	while(i < pagenum * pagesize)
	{
		if(*(unsigned int*)(FuncFFT_10_Orig + i) == 0x90909090)
		{
			size = i;
			break;
		}		
		i ++;
	}
	
	memcpy(CodeFFT_10, (void*)FuncFFT_10_Orig, size + 20);
	i = 0;
	while(i < size + 20)
	{
		CodePtr = (char*)CodeFFT_10 + i;
		id = (unsigned short int*)CodePtr;
		if(*(unsigned int *)(CodePtr) == 0x90909090)
		{
			//nopnopnopnop means exit.
			break;
		}
		switch(*id)
		{
			case 0xFFFF:
				//R[]
				*id = 0;
				*(unsigned int *)(id - 1) = *(unsigned int *)(id - 1) * 4 + (unsigned int)FRe;
				i ++;
				break;
			case 0xFFFE:
				//I[]
				*id = 0;
				*(unsigned int *)(id - 1) = *(unsigned int *)(id - 1) * 4 + (unsigned int)FIm;
				i ++;
				break;
			case 0xFFEF:
				//SINT
				*id = 0;
				*(unsigned int *)(id - 1) = *(unsigned int *)(id - 1) * 4 + (unsigned int)FFT_Static_10_Sin;
				i ++;
				break;
			case 0xFFEE:
				//COST
				*id = 0;
				*(unsigned int *)(id - 1) = *(unsigned int *)(id - 1) * 4 + (unsigned int)FFT_Static_10_Cos;
				i ++;
				break;
		}
		i ++;
	}

	
	//FFT_Init();
	for(i = 0;i < 100000;i ++)
	{
		StaticReverseArrange_10(FRe, TRe);
		StaticReverseArrange_10(FIm, TIm);
		FuncFFT_10();
		
	}

	free(CodeFFT_10);
	free(TRe);
	free(TIm);
	free(FRe);
	free(FIm);
	return (0);
}

