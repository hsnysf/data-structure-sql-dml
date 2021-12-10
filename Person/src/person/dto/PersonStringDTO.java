package person.dto;

import person.annotation.Column;

public class PersonStringDTO {
	
	@Column("prsn_id")
	private String id;
	@Column("prsn_name")
	private String name;
	@Column("prsn_gender")
	private String gender;
	@Column("prsn_age")
	private String age;
	@Column("prsn_cpr")
	private String cpr;
	@Column("prsn_account_no")
	private String accountNo;
	@Column("prsn_gpa")
	private String gpa;
	@Column("prsn_salary")
	private String salary;
	@Column("prsn_annual_income")
	private String annualIncome;
	@Column("prsn_date_of_birth")
	private String dateOfBirth;
	@Column("prsn_registration_date_time")
	private String registrationDateTime;
	@Column("prsn_sleep_time")
	private String sleepString;
	@Column("prsn_graduated")
	private String graduated;
	@Column("prsn_certificates")
	private String certificates;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCpr() {
		return cpr;
	}
	public void setCpr(String cpr) {
		this.cpr = cpr;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getGpa() {
		return gpa;
	}
	public void setGpa(String gpa) {
		this.gpa = gpa;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getRegistrationDateTime() {
		return registrationDateTime;
	}
	public void setRegistrationDateTime(String registrationDateTime) {
		this.registrationDateTime = registrationDateTime;
	}
	public String getSleepString() {
		return sleepString;
	}
	public void setSleepString(String sleepString) {
		this.sleepString = sleepString;
	}
	public String getGraduated() {
		return graduated;
	}
	public void setGraduated(String graduated) {
		this.graduated = graduated;
	}
	public String getCertificates() {
		return certificates;
	}
	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", cpr=" + cpr
				+ ", accountNo=" + accountNo + ", gpa=" + gpa + ", salary=" + salary + ", annualIncome=" + annualIncome
				+ ", dateOfBirth=" + dateOfBirth + ", registrationDateTime=" + registrationDateTime + ", sleepString="
				+ sleepString + ", graduated=" + graduated + ", certificates=" + certificates + "]";
	}
}
