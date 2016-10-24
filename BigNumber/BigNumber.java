package com.example.generic.integer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * 
 */

public class BigNumber {

	final static int LONGLEN=18;
	private String number;

	private List<Long> listNumber=new LinkedList<Long>();


	public BigNumber(String number) {
		listNumber=convertToList(number);
		this.number=number;
	}



	public String getNumber() {
		return number;
	}


	public BigNumber add(String number){

		int ALen=this.listNumber.size();
		Iterator<Long> AIter=this.listNumber.iterator();


		BigNumber bn=new BigNumber(number);
		int BLen=bn.listNumber.size();
		Iterator<Long> BIter=bn.listNumber.iterator();

		Long carry=0L;

		BigNumber result=new BigNumber("0");
		result.listNumber.clear();


		Long temp=0L;
		if(ALen>BLen){
			while(BIter.hasNext()){
				temp=AIter.next()+BIter.next()+carry;
				result.listNumber.add(0,temp);
				if(temp.toString().length()>LONGLEN){
					carry=Long.parseLong(temp.toString().substring(0, 1));
				}
			}

			while(AIter.hasNext()){
				result.listNumber.add(0,AIter.next()+carry);
				carry=0L;
			}
		}
		else{
			while(AIter.hasNext()){
				temp=AIter.next()+BIter.next()+carry;
				if(temp.toString().length()>LONGLEN){
					carry=Long.parseLong(temp.toString().substring(0, 1));
					temp=Long.parseLong(temp.toString().substring(1));
				}
				result.listNumber.add(0,temp);
			}

			while(BIter.hasNext()){
				result.listNumber.add(0,BIter.next()+carry);
				carry=0L;
			}
		}


		StringBuilder sb=new StringBuilder();

		List<Long> listLong=result.listNumber;

		for(Long l:listLong){
			sb.append(l.toString());
		}
		result.number=sb.toString();

		return result;





	}


	private List<Long> convertToList(String num){
		List<Long> tempList=new LinkedList<Long>();
		int len=num.length();

		int endLen=len;
		int startLen=len-LONGLEN;
		if(startLen<=0){
			tempList.add(Long.parseLong(num));
			return tempList;
		}

		while(endLen>LONGLEN){
			String s=num.substring(startLen, endLen);
			tempList.add(Long.parseLong(s));
			endLen=startLen;
			startLen=endLen-LONGLEN;
		}
		tempList.add(Long.parseLong(num.substring(0, endLen)));
		return tempList;

	}

	public static void main(String[] args) {
		BigNumber bn=new BigNumber("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
		BigNumber bbn=bn.add("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
		System.out.print(bbn.getNumber());
	}
}
