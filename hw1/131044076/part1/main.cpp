
#include <iostream>
#include <string>
#include "Bisection.h"
#include "math.h"
using namespace std;

double question1(double x){
	double result=3*x-exp(x);
	return result;

}
double question2(double x){
	
	double result=2*x +3*cos(x)-exp(x) ;
	return result;

}
double question3(double x){
	double result=pow(x,2)-4*pow(x,1)+4 -log(x) ;
	return result;

}
double question4(double x){
	double pi = 3.14159;
	double result=pow(x,1)+1-2*sin(pi*x)  ;
	return result;

}


int main(){
	bool exit=false;
	double (*fpoint)(double);
	double begin,end;
	int epsi;
	int qnumber;
	string typeName="";
	
	cout <<"Enter Question Number"<<endl;

	cin>>qnumber;
	if(qnumber==1)
		fpoint=question1;
	else if(qnumber==2)
		fpoint=question2;
	else if(qnumber==3)
		fpoint=question3;
	else if(qnumber==4)
		fpoint=question4;
	cout<<"Enter Begin point:"<<endl;
	cin>>begin;
	cout<<"Enter End Point"<<endl;
	cin>>end;
	cout<<"Enter Stop Criteria;"<<endl;

	cin>>typeName;
	cout<<"Enter Epsilone 10^?"<<endl;
	cin>>epsi;
	if(qnumber>0&& qnumber<5){
		BiSection deneme(fpoint,typeName,epsi);
		deneme.calculateRoots(begin,end,cout);
	}
	
	return 0;
}

