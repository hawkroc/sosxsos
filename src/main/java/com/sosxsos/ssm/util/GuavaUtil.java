package com.sosxsos.ssm.util;

import java.util.List;
import java.util.Map;

import com.google.api.client.repackaged.com.google.common.base.Joiner;
import com.google.api.client.util.Lists;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;


public final class GuavaUtil {
/*
 * String s = getString();
Strings.isNullOrEmpty(s);
Strings.nullToEmpty(s);
Strings.repeat("-", 70);
 */
	
//	List -> String
//	List<String> parts = Arrays.asList("a", "b", "c", null);
//	String joined = Joiner.on(", ").skipNulls().join(parts);
//	assertThat(joined, is("a, b, c"));
//	
	public static String listToString(final List<String> input,String join){
		return Joiner.on(join).skipNulls().join(input);
	}
	
	
	
//	 String -> Map
//	MapSplitter splitter = Splitter.on(" ").withKeyValueSeparator(":");
//	splitter.split("a:1 b:2"); // => Map {a=1, b=2}
	
	public static Map<String,String> stringToMap(final String input,String split,String key_spli){
		return Splitter.on(split).withKeyValueSeparator(key_spli).split(input);
	}
	
	
//	FluentIterable.from(persons)
//    .filter((Person p) -> p.getAge() >= 18)
//    .transform((Person p) -> p.getName())
//    .toList();
	
	
//	
//	java.util.Optional<Person> optional = getPerson();
//
//	String name = optional.map(p -> p.getName()).orElse("Unknown");
//
//	String parentName = optional.flatMap(p -> p.getParent())
//	        .map(p -> p.getName())
//	        .orElse("Unknown Parent");
	
	public static void main(String arg[]){
		List<String> test =Lists.newArrayList();
		FluentIterable.from(test)
		.filter((String t) -> t.equals("abc"));
		
	//	test.stream().filter((String t) -> t.equals("abc")).map(mapper)
	}
	
	   
	
}
