#include "Bisection.h"
#include <algorithm>
double BiSection::convertPositive(double number){
	if (number<0)
		return -number;
	else
		return number;
}
double BiSection::calculateRoots(double first,double last,ostream& output){
	double begx,endx;
	double begy,endy;
	double centerx,centery;
	bool found=false;
	begx=first;
	endx=last;
	centery=functionPointer(first);
	output<<"n"<<"\ta(n)\t"<<"\tb(n)\t"<<"\tp(n)\t"<<"\tf(p(n))\t"<<endl;
	output<<"--------------------------------------------------------------"<<endl;
	int i;
	for (i = 0; i < 100; ++i)
	{
		centerx=(begx+endx)/2.0;
		begy=functionPointer(begx);
		endy=functionPointer(endx);
		centery=functionPointer(centerx);
		output<<"n="<<i+1<<"\ta(n)=\t"<<begx<<"\tb(n)=\t"<<endx<<"\tp(n)="<<centerx<<"\tf(p(n)=\t"<<centery<<endl;
		if(stopCriteria(begx,endx)<epsilone)
		{

			found=true;
			output<<"Approximated Root;"<<centerx<<endl;
			output<<"n="<<i+1<<"\ta(n)=\t"<<begx<<"\tb(n)=\t"<<endx<<"\tp(n)="<<centerx<<"\tf(p(n)=\t"<<centery<<endl;
			break;

		}
		
		if(begy*centery<=0){
			endx=centerx;
			
		}
		else if(begy*centery>=0)
			begx=centerx;


	}
	if(i==100 && !found){
		output<<"Root is not found!!"<<endl;
	}
	if(found)
		arr.push_back(centerx);
	return centerx;
}

double BiSection::stopCriteria(double bx,double ex){
	
	if(SCriteriaType=="DISTANCE_TO_ROOT"){

		return convertPositive(functionPointer((ex+bx)/2));



	}else if(SCriteriaType=="ABSOLUTE_ERROR"){

		return convertPositive((bx-ex));


	}
	
	return (convertPositive(bx-ex)/convertPositive(min(bx,ex)));



}