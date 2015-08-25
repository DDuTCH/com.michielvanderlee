package com.michielvanderlee.java.preferences;

import java.util.prefs.Preferences;

/**
 * 
 * Preferences can not be shipped with an application but are great for storing and retrieve system and user settings. 
 * So you need to define default values elsewhere, but after that you can easily allow users to edit them and they will be stored independently.
 * 
 * @author MichielV
 * {@link http://www.vogella.com/tutorials/JavaPreferences/article.html}
 */
public class PreferencesDemo
{
	private Preferences systemPrefs;
	private Preferences userPrefs;
	
	/**
	 * Uses system and user preferences.
	 * Since system preferences are stored in the windows registry this might not work if java does not have permissions.
	 */
	public void setPreferences()
	{
		systemPrefs = Preferences.systemRoot().node(this.getClass().getName());
		userPrefs = Preferences.userRoot().node(this.getClass().getName());
		
		String ID1 = "Test1";
		String ID2 = "Test2";
		String ID3 = "Test3";
		
		System.out.println(userPrefs.getBoolean(ID1, systemPrefs.getBoolean(ID1, true)));
		System.out.println(userPrefs.get(ID2, systemPrefs.get(ID2, "Hello world")));
		System.out.println(userPrefs.getInt(ID3, systemPrefs.getInt(ID3, 50)));
		
		systemPrefs.putBoolean(ID1, false);
		systemPrefs.put(ID2, "Hello Canada");
		systemPrefs.putInt(ID3, 42);
		userPrefs.putInt(ID3, 24);
		
		systemPrefs.remove(ID1);
	}
	
	/**
	 * Users preferences only.
	 */
	public void setUserPreferences()
	{
		userPrefs = Preferences.userRoot().node(this.getClass().getName());
		
		String ID1 = "Test1";
		String ID2 = "Test2";
		String ID3 = "Test3";
		
		System.out.println(userPrefs.getBoolean(ID1, true));
		System.out.println(userPrefs.get(ID2, "Hello World"));
		System.out.println(userPrefs.getInt(ID3, 50));
		
		userPrefs.putBoolean(ID1, false);
		userPrefs.put(ID2, "Hello Canada");
		userPrefs.putInt(ID3, 24);
		
		userPrefs.remove(ID1);
	}
	
	
	public static void main(String[] args)
	{
		PreferencesDemo demo = new PreferencesDemo();
		demo.setUserPreferences();
	}
}
