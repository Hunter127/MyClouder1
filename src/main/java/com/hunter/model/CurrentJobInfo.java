package com.hunter.model;

import java.io.IOException;
import java.io.Serializable;

import org.apache.hadoop.mapred.JobStatus;
import org.apache.hadoop.mapred.RunningJob;

public class CurrentJobInfo implements Serializable, Comparable<CurrentJobInfo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jobId;
	private String jobName;
	private float mapProgress;
	private float redProgress;
	private String runState;
	private long startTime;
	
    public CurrentJobInfo(RunningJob runningJob,long startTime,int runStateInt) throws IOException {
		this.jobId = runningJob.getID().toString();
		this.jobName = runningJob.getJobName();
		this.mapProgress = runningJob.mapProgress();
		this.redProgress = runningJob.reduceProgress();
		this.startTime = startTime;
		this.runState = JobStatus.getJobRunState(runStateInt);
		
		
	}
	@Override
	public int compareTo(CurrentJobInfo o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
