package com.ixion.text;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Saves {
	
	private boolean firstLevel,secondLevel,thirdLevel,fourthLevel,fifthLevel;
	java.io.File levelFile1 = new java.io.File("res/Saves/save1.txt");
	java.io.File levelFile2 = new java.io.File("res/Saves/save2.txt");
	java.io.File levelFile3 = new java.io.File("res/Saves/save3.txt");	
	java.io.File deaths1 = new java.io.File("res/Saves/deaths1.txt");
	java.io.File deaths2 = new java.io.File("res/Saves/deaths2.txt");
	java.io.File deaths3 = new java.io.File("res/Saves/deaths3.txt");
	
	private int deathsLvl1 = 0, deathsLvl2 = 0, deathsLvl3 = 0;
	
	private boolean load1 = false, load2 = false, load3 = false;
	
	public Saves()
	{
		firstLevel = false;
		secondLevel = false;
		thirdLevel = false;
		fourthLevel = false;
		fifthLevel = false;
	}
	
	public void WhatLoad(int load)
	{
		if(load == 1)
			load1 = true;
		else if(load == 2)
			load2 = true;
		else if(load == 3)
			load3 = true;
	}
	public void ReadLevel(int whatLoad)
	{
		if(whatLoad == 1)
		{
			try
			{
				Scanner read = new Scanner(levelFile1);
				while(read.hasNext())
				{
					String lvl = read.nextLine();
					String lvl1 = "1";
					String lvl2 = "2";
					String lvl3 = "3";
					String lvl4 = "4";
					
					if(lvl.equals(lvl1))
					{
						firstLevel = true;
						secondLevel = false;
					}
					
					if(lvl.equals(lvl2))
					{
						firstLevel = false;
						secondLevel = true;
					}
					
					if(lvl.equals(lvl3))
					{
						firstLevel = false;
						secondLevel = false;
						thirdLevel = true;
					}
					if(lvl.equals(lvl4))
					{
						firstLevel = false;
						secondLevel = false;
						thirdLevel = false;
						fourthLevel = true;
					}
				}
			} catch (Exception e)
			{
				System.out.println("could not find the level");
			}
		}
		else if(whatLoad == 2)
		{
			try
			{
				Scanner read = new Scanner(levelFile2);
				while(read.hasNext())
				{
					String lvl = read.nextLine();
					String lvl1 = "1";
					String lvl2 = "2";
					String lvl3 = "3";
					String lvl4 = "4";
					
					if(lvl.equals(lvl1))
					{
						firstLevel = true;
						secondLevel = false;
					}
					
					if(lvl.equals(lvl2))
					{
						firstLevel = false;
						secondLevel = true;
					}
					
					if(lvl.equals(lvl3))
					{
						firstLevel = false;
						secondLevel = false;
						thirdLevel = true;
					}
					if(lvl.equals(lvl4))
					{
						firstLevel = false;
						secondLevel = false;
						thirdLevel = false;
						fourthLevel = true;
					}
				}
			} catch (Exception e)
			{
				System.out.println("could not find the level");
			}	
		}
		else if(whatLoad == 3)
		{
			try
			{
				Scanner read = new Scanner(levelFile3);
				while(read.hasNext())
				{
					String lvl = read.nextLine();
					String lvl1 = "1";
					String lvl2 = "2";
					String lvl3 = "3";
					String lvl4 = "4";
					
					if(lvl.equals(lvl1))
					{
						firstLevel = true;
						secondLevel = false;
					}
					
					if(lvl.equals(lvl2))
					{
						firstLevel = false;
						secondLevel = true;
					}
					
					if(lvl.equals(lvl3))
					{
						firstLevel = false;
						secondLevel = false;
						thirdLevel = true;
					}
					if(lvl.equals(lvl4))
					{
						firstLevel = false;
						secondLevel = false;
						thirdLevel = false;
						fourthLevel = true;
					}
				}
			} catch (Exception e)
			{
				System.out.println("could not find the level");
			}	
		}
	}

	public void Erase(int whatLoad)
	{
		if(whatLoad == 1)
		{
			try
			{
				String levelNumberDelete = "1";
				FileWriter stream1 = new FileWriter("res/Saves/save1.txt");
				BufferedWriter out1 = new BufferedWriter(stream1);
				out1.write(levelNumberDelete);
				out1.close();
			} catch (Exception e)
			{
				System.out.println("could not delete");
			}
		}
		
		if(whatLoad == 2)
		{
			try
			{
				String levelNumberDelete2 = "1";
				FileWriter stream2 = new FileWriter("res/Saves/save2.txt");
				BufferedWriter out2 = new BufferedWriter(stream2);
				out2.write(levelNumberDelete2);
				out2.close();
			} catch (Exception e)
			{
				System.out.println("could not delete");
			}
		}
		
		if(whatLoad == 3)
		{
			try
			{
				String levelNumberDelete3 = "1";
				FileWriter stream3 = new FileWriter("res/Saves/save3.txt");
				BufferedWriter out3 = new BufferedWriter(stream3);
				out3.write(levelNumberDelete3);
				out3.close();
			} catch (Exception e)
			{
				System.out.println("could not delete");
			}
		}
	}
	
	public void SaveLevel(int level, int load)
	{
		if(load == 1)
		{
			try
			{
				System.out.println("gon");
				String levelNumber = "" + level;
				FileWriter stream = new FileWriter("res/Saves/save1.txt");
				BufferedWriter out = new BufferedWriter(stream);
				out.write(levelNumber);
				out.close();
			} catch (Exception e)
			{
				System.out.println("could not output");
			}
		}
		else if(load == 2)
		{
			try
			{
				System.out.println("gon");
				String levelNumber = "" + level;
				FileWriter stream = new FileWriter("res/Saves/save1.txt");
				BufferedWriter out = new BufferedWriter(stream);
				out.write(levelNumber);
				out.close();
			} catch (Exception e)
			{
				System.out.println("could not output");
			}
		}
		else if(load == 3)
		{
			try
			{
				System.out.println("gon");
				String levelNumber = "" + level;
				FileWriter stream = new FileWriter("res/Saves/save1.txt");
				BufferedWriter out = new BufferedWriter(stream);
				out.write(levelNumber);
				out.close();
			} catch (Exception e)
			{
				System.out.println("could not output");
			}
		}
		/*		
		if(level == 2 && load == 1)
		{
				try
				{
					System.out.println("gon");
					String levelNumber = "2";
					FileWriter stream = new FileWriter("res/Saves/save1.txt");
					BufferedWriter out = new BufferedWriter(stream);
					out.write(levelNumber);
					out.close();
				} catch (Exception e)
				{
					System.out.println("could not output");
				}
		}
		if(level == 2 && load == 2)
		{
				try
				{
					System.out.println("gon");
					String levelNumber = "2";
					FileWriter stream = new FileWriter("res/Saves/save2.txt");
					BufferedWriter out = new BufferedWriter(stream);
					out.write(levelNumber);
					out.close();
				} catch (Exception e)
				{
					System.out.println("could not output");
				}
		}
		if(level == 2 && load == 3)
		{
				try
				{
					System.out.println("gon");
					String levelNumber = "2";
					FileWriter stream = new FileWriter("res/Saves/save3.txt");
					BufferedWriter out = new BufferedWriter(stream);
					out.write(levelNumber);
					out.close();
				} catch (Exception e)
				{
					System.out.println("could not output");
				}
		}
		if(level == 3 && load == 1)
		{
				try
				{
					System.out.println("goning");
					String levelNumber = "3";
					FileWriter stream = new FileWriter("res/Saves/save1.txt");
					BufferedWriter out = new BufferedWriter(stream);
					out.write(levelNumber);
					out.close();
				} catch (Exception e)
				{
					System.out.println("could not output");
				}
		}
		if(level == 3 && load == 2)
		{
				try
				{
					System.out.println("gon");
					String levelNumber = "3";
					FileWriter stream = new FileWriter("res/Saves/save2.txt");
					BufferedWriter out = new BufferedWriter(stream);
					out.write(levelNumber);
					out.close();
				} catch (Exception e)
				{
					System.out.println("could not output");
				}
		}
		if(level == 3 && load == 3)
		{
				try
				{
					System.out.println("gon");
					String levelNumber = "3";
					FileWriter stream = new FileWriter("res/Saves/save3.txt");
					BufferedWriter out = new BufferedWriter(stream);
					out.write(levelNumber);
					out.close();
				} catch (Exception e)
				{
					System.out.println("could not output");
					e.printStackTrace();
				}
		}
		*/
	}
	
	public int WhatLevel()
	{
		if(firstLevel)
			return 1;
		else if(secondLevel)
			return 2;
		else if(thirdLevel)
			return 3;
		else if(fourthLevel)
			return 4;
		else if(fifthLevel)
			return 5;
		
		return 1;
	}
	
}
