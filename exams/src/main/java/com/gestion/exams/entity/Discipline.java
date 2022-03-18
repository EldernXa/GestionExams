package com.gestion.exams.entity;

public enum Discipline {
	INFORMATIQUE, MATH, SPORT, PHYSIQUE, AUTRE;

	// TODO à tester
	public String toString(Discipline discipline) {

		switch(discipline) {
			case INFORMATIQUE:
				return "Informatique";
			case MATH:
				return "Mathématique";
			case SPORT:
				return "Sport";
			case PHYSIQUE:
				return "Physique";
			case AUTRE:
					return "Autre";
			default:
				return null;
		}
	}

	// TODO à tester
	public Discipline toEnum(String stringToEnum) {

		switch(stringToEnum.toLowerCase()) {
			case "informatique":
				return INFORMATIQUE;
			case "mathématique":
				return MATH;
			case "sport":
				return SPORT;
			case "physique":
				return PHYSIQUE;
			case "autre":
				return AUTRE;
			default:
				return null;
			}
	}

}
