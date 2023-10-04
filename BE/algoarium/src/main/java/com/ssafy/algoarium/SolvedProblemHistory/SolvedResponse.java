package com.ssafy.algoarium.SolvedProblemHistory;

import java.util.List;

import lombok.Data;

@Data
public class SolvedResponse {
	private int count;
	private List<ProblemItem> items;

	// getter와 setter
}

@Data
class ProblemItem {
	private int problemId;
	private String titleKo;
	private List<Title> titles;
	private boolean isSolvable;
	private boolean isPartial;
	private int acceptedUserCount;
	private int level;
	private int votedUserCount;
	private boolean sprout;
	private boolean givesNoRating;
	private boolean isLevelLocked;
	private double averageTries;
	private boolean official;
	private List<Tag> tags;

}

@Data
class Title {
	private String language;
	private String languageDisplayName;
	private String title;
	private boolean isOriginal;

	// getter와 setter
}

@Data
class Tag {
	private String key;
	private boolean isMeta;
	private int bojTagId;
	private int problemCount;
	private List<DisplayName> displayNames;
	private List<String> aliases;

	// getter와 setter
}

@Data
class DisplayName {
	private String language;
	private String name;
	private String shortName;

	// getter와 setter
}