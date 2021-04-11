package ru.bspb.reqres;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.crypto.Data;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User{

	@JsonProperty("createdAt")
	private Date createdAt;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("job")
	private String job;

	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
	}

	public Date getCreatedAt(){
		return createdAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setJob(String job){
		this.job = job;
	}

	public String getJob(){
		return job;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"createdAt = '" + createdAt + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",job = '" + job + '\'' + 
			"}";
		}
}