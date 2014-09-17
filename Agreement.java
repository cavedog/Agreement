package AgreementMaker;

import java.sql.Date;

public class Agreement {
	private int numberOfAgreementP;
	private String dateOfAgreementP;
	private String surnameP;
	private String nameP;
	private String SecondNameP;
	private String passportNumberP;
	private String passportIssuanceP;
	private String innP;
	private String adressP;
	private String phoneP;
	private String eMailP;
	private String nameOfCourseP;
	private String dateOfStartP;
	private int trainingPeriodP;
	private double costTrainingP;
	private double costTrainingPart1P;
	private String ddate1P;
	private double costTrainingPart2P;
	private String ddate2P;
	private double costTrainingPart3P;
	private String ddate3P;
	
    public Agreement(int numberOfAgreement, String dateOfAgreement, String surname, String name, String SecondName,String passportNumber, String passportIssuance,String inn,
			String adress,String phone,String eMail, String nameOfCourse, String dateOfStart, int trainingPeriod, double costTraining, double costTrainingPart1, String ddate1,
			double costTrainingPart2, String ddate2, double costTrainingPart3, String ddate3) {
    	numberOfAgreementP=numberOfAgreement;
    	dateOfAgreementP= dateOfAgreement;
    	surnameP=surname;
    	nameP=name;
    	SecondNameP=SecondName;
    	passportNumberP=passportNumber;
    	passportIssuanceP=passportIssuance;
    	innP=inn;
    	adressP=adress;
    	phoneP=phone;
    	eMailP=eMail;
    	nameOfCourseP=nameOfCourse;
    	dateOfStartP=dateOfStart;
    	trainingPeriodP=trainingPeriod;
    	costTrainingP=costTraining;
    	costTrainingPart1P=costTrainingPart1;
    	setDdate1P(ddate1);
    	costTrainingPart2P=costTrainingPart2;
    	setDdate2P(ddate2);
    	costTrainingPart3P=costTrainingPart3;
    	setDdate3P(ddate3);
    	}

	
public String getSurnameP() {
	return surnameP;
}

public void setSurnameP(String surnameP) {
	this.surnameP = surnameP;
}

public String getNameP() {
	return nameP;
}

public void setNameP(String nameP) {
	this.nameP = nameP;
}

public String getSecondNameP() {
	return SecondNameP;
}

public void setSecondNameP(String secondNameP) {
	SecondNameP = secondNameP;
}

public String getPassportNumberP() {
	return passportNumberP;
}

public void setPassportNumberP(String passportNumberP) {
	this.passportNumberP = passportNumberP;
}

public String getPassportIssuanceP() {
	return passportIssuanceP;
}

public void setPassportIssuanceP(String passportIssuanceP) {
	this.passportIssuanceP = passportIssuanceP;
}

public String getInnP() {
	return innP;
}

public void setInnP(String innP) {
	this.innP = innP;
}

public String getAdressP() {
	return adressP;
}

public void setAdressP(String adressP) {
	this.adressP = adressP;
}

public String geteMailP() {
	return eMailP;
}

public void seteMailP(String eMailP) {
	this.eMailP = eMailP;
}

public String getNameOfCourseP() {
	return nameOfCourseP;
}

public void setNameOfCourseP(String nameOfCourseP) {
	this.nameOfCourseP = nameOfCourseP;
}


public int getTrainingPeriodP() {
	return trainingPeriodP;
}


public void setTrainingPeriodP(int trainingPeriodP) {
	this.trainingPeriodP = trainingPeriodP;
}


public double getCostTraining() {
	return costTrainingP;
}


public void setCostTraining(double costTraining) {
	this.costTrainingP = costTraining;
}


public double getCostTrainingPart1P() {
	return costTrainingPart1P;
}


public void setCostTrainingPart1P(double costTrainingPart1P) {
	this.costTrainingPart1P = costTrainingPart1P;
}


public double getCostTrainingPart2P() {
	return costTrainingPart2P;
}


public void setCostTrainingPart2P(double costTrainingPart2P) {
	this.costTrainingPart2P = costTrainingPart2P;
}


public double getCostTrainingPart3P() {
	return costTrainingPart3P;
}


public void setCostTrainingPart3P(double costTrainingPart3P) {
	this.costTrainingPart3P = costTrainingPart3P;
}


public String getDateOfAgreementP() {
	return dateOfAgreementP;
}


public void setDateOfAgreementP(String dateOfAgreementP) {
	this.dateOfAgreementP = dateOfAgreementP;
}


public String getDateOfStartP() {
	return dateOfStartP;
}


public void setDateOfStartP(String dateOfStartP) {
	this.dateOfStartP = dateOfStartP;
}


public int getNumberOfAgreementP() {
	return numberOfAgreementP;
}


public void setNumberOfAgreementP(int numberOfAgreementP) {
	this.numberOfAgreementP = numberOfAgreementP;
}


public String getPhoneP() {
	return phoneP;
}


public void setPhoneP(String phoneP) {
	this.phoneP = phoneP;
}


public String getDdate1P() {
	return ddate1P;
}


public void setDdate1P(String ddate1p) {
	ddate1P = ddate1p;
}


public String getDdate2P() {
	return ddate2P;
}


public void setDdate2P(String ddate2p) {
	ddate2P = ddate2p;
}


public String getDdate3P() {
	return ddate3P;
}


public void setDdate3P(String ddate3p) {
	ddate3P = ddate3p;
}
	
	}
		

