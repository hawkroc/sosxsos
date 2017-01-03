package com.sosxsos.ssm.dto;

import java.util.List;

public class ResidentsListRes implements ResBase {
private int residents;

public int getResidents() {
	return residents;
}
public void setResidents(int residents) {
	this.residents = residents;
}
public List<Resident> getList() {
	return list;
}
public void setList(List<Resident> list) {
	this.list = list;
}
private List<Resident> list;
}
