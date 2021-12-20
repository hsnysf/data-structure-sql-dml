package person.dto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import person.annotation.Column;

public class PersonPrimitiveDateDTO {

	@Column("prsn_id")
	private int id;
	@Column("prsn_name")
	private String name;
	@Column("prsn_gender")
	private char gender;
	@Column("prsn_age")
	private short age;
	@Column("prsn_cpr")
	private int cpr;
	@Column("prsn_account_no")
	private long accountNo;
	@Column("prsn_gpa")
	private float gpa;
	@Column("prsn_salary")
	private double salary;
	@Column("prsn_annual_income")
	private BigDecimal annualIncome;
	@Column("prsn_date_of_birth")
	private Date dateOfBirth;
	@Column("prsn_registration_date_time")
	private Date registrationDateTime;
	@Column("prsn_sleep_time")
	private Date sleepTime;
	@Column("prsn_graduated")
	private boolean graduated;
	@Column("prsn_certificates")
	private String[] certificates;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public short getAge() {
		return age;
	}
	public void setAge(short age) {
		this.age = age;
	}
	public int getCpr() {
		return cpr;
	}
	public void setCpr(int cpr) {
		this.cpr = cpr;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public float getGpa() {
		return gpa;
	}
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getRegistrationDateTime() {
		return registrationDateTime;
	}
	public void setRegistrationDateTime(Date registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}
	public Date getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(Date sleepTime) {
		this.sleepTime = sleepTime;
	}
	public boolean getGraduated() {
		return graduated;
	}
	public void setGraduated(boolean graduated) {
		this.graduated = graduated;
	}
	public String[] getCertificates() {
		return certificates;
	}
	public void setCertificates(String[] certificates) {
		this.certificates = certificates;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", cpr="
				+ cpr + ", accountNo=" + accountNo + ", gpa=" + gpa + ", salary=" + salary + ", annualIncome="
				+ annualIncome + ", dateOfBirth=" + dateOfBirth + ", registrationDateTime=" + registrationDateTime
				+ ", sleepTime=" + sleepTime + ", graduated=" + graduated + ", certificates=" + Arrays.toString(certificates) + "]";
	}
}
