//Author : Xingyu LI
//Get help from wiki

#include <iostream>
#include <algorithm>
using namespace std;

void max_heapify(int arr[], int start, int end) {
	
	int dad = start;
	int son = dad * 2 + 1;
	while (son < end) { 
		if (son + 1 < end && arr[son] < arr[son + 1]) 
			son++;
		if (arr[dad] > arr[son]) 
			return;
		else { 
			swap(arr[dad], arr[son]);
			dad = son;
			son = dad * 2 + 1;
		}
	}
}

void heap_sort(int arr[], int len) {

	for (int i = len / 2 - 1; i >= 0; i--)
		max_heapify(arr, i, len);
	
	for (int i = len - 1; i > 0; i--) {
		swap(arr[0], arr[i]);
		max_heapify(arr, 0, i);
	}
}

int main() {
	int a[]={1,8,2,6,5,4,3,2,7,4,1,8,7,9,5,45,4,5,26,54,23,12,47,41,12};
	int len=sizeof(a)/sizeof(*a);
	cout<<"len = "<<len<<endl;
	for(int i=0;i<len;i++)
		cout<<a[i]<<" ";
	cout<<endl;
	heap_sort(a,len);
	for(int i=0;i<len;i++)
		cout<<a[i]<<" ";
	cout<<endl;
}