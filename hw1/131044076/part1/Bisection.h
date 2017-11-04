#ifndef BISECTION_H
#define BISECTION_H
#include <iostream>
#include <string>
#include <vector>
#include <stdlib.h>
#include "math.h"
using namespace std;

class BiSection
{

public:
	BiSection(double (*funcPointer)(double),string type,double ep):SCriteriaType(type),functionPointer(funcPointer),epsilone(pow(10,ep)){	
		

	}
	double calculateRoots(double first,double last,ostream& output);
	double getRoot(int index)  const{
		if(index>=0 && index<arr.size())
			return arr[index];
	}
	int totalRootSize()const{
		return arr.size();

	}

private:
	string SCriteriaType;
	vector<double> arr;
	 double epsilone;
	double (*functionPointer)(double) ;
	double stopCriteria(double bx,double ex);
	double convertPositive(double number);
};


#endif