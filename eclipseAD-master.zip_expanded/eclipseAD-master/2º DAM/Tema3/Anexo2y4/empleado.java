package Anexo2y4;

import java.io.Serializable;
import java.sql.Date;

public class empleado implements Serializable{

	private int workerNumber;
	private String workerName; 
	private String job; 
	private int mgr; 
	private Date hiredate; 
	private double sal; 
	private double comm;
	private int departamentNumber;
	
	public empleado(int workerNumber, String workerName, String job, int mgr, Date hiredate, double sal, double comm,
			int departamentNumber) {

		this.workerNumber = workerNumber;
		this.workerName = workerName;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.departamentNumber = departamentNumber;
		
	}
	
	public empleado() {}

	public int getWorkerNumber() {
		return workerNumber;
	}

	public void setWorkerNumber(int workerNumber) {
		this.workerNumber = workerNumber;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public double getComm() {
		return comm;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}

	public int getDepartamentNumber() {
		return departamentNumber;
	}

	public void setDepartamentNumber(int departamentNumber) {
		this.departamentNumber = departamentNumber;
	}

	@Override
	public String toString() {
		return "empleado [workerNumber=" + workerNumber + ", workerName=" + workerName + ", job=" + job + ", mgr=" + mgr
				+ ", hiredate=" + hiredate + ", sal=" + sal + ", comm=" + comm + ", departamentNumber="
				+ departamentNumber + "]" + "\n";
	}

}
