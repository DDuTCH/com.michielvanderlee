package com.michielvanderlee.enhancer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.bcel.Constants;
import org.apache.bcel.Repository;
import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LDC;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.Type;

public class Enhancer
{
	// ********************************************************************
	// Constructors
	// ********************************************************************

	// ********************************************************************
	// Methods
	// ********************************************************************
	public static void main(String[] args)
	{
		try
		{
			baseClass = args[0];
			File directory = new File(args[1]);
			if(directory.isDirectory())
			{
				addClassesToRepository(directory);
			}
			
			for(String className : classNames.keySet() )
			{
				JavaClass jc = Repository.lookupClass(className);
				if(!jc.getClassName().equals(baseClass) && Repository.instanceOf(jc, baseClass))
				{
					ClassGen cg = new ClassGen(jc);
					addCapabilityMethod(cg, "Read");
					addCapabilityMethod(cg, "Write");
					addCapabilityMethod(cg, "Delete");
					
					FileOutputStream fos = new FileOutputStream( new File(classNames.get(className)) );
					cg.getJavaClass().dump(fos);
					fos.close();
				}
			}			

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void addClassesToRepository(File dir) throws ClassFormatException, FileNotFoundException, IOException
	{
		for(File f : dir.listFiles())  
		{
			if(f.isDirectory())
			{
				addClassesToRepository(f);
			}
			else if(f.getName().endsWith(".class"))
			{
				JavaClass jc = new ClassParser(new FileInputStream(f), f.getName()).parse();
				Repository.addClass(jc);
				classNames.put(jc.getClassName(), f.getPath());
			}
		}
	}
	
	private static void addCapabilityMethod(ClassGen cg, String capability)	
	{
		ConstantPoolGen cp = cg.getConstantPool();
		
		InstructionFactory factory = new InstructionFactory(cg);
		InstructionList il = new InstructionList();
		
		il.append(new LDC(cp.lookupClass(cg.getClassName())));
		il.append(factory.createInvoke("java.lang.Class", "getName", Type.STRING, Type.NO_ARGS, Constants.INVOKEVIRTUAL));
		il.append(new LDC(cp.addString(String.format(".%s", capability))));
		il.append(factory.createInvoke("java.lang.String", "concat", Type.STRING, new Type[] {Type.STRING }, Constants.INVOKEVIRTUAL));
		il.append(InstructionFactory.createReturn(Type.STRING));
		
		MethodGen mg = new MethodGen(Constants.ACC_PUBLIC, Type.STRING, null, null, 
				String.format("get%sCapability",capability), cg.getClassName(), il, cp);
		mg.setMaxStack();
		mg.setMaxLocals();
		
		cg.addMethod(mg.getMethod());
		il.dispose();
	}
	
	// ********************************************************************
	// getters and setters.
	// ********************************************************************

	// ********************************************************************
	// Properties
	// ********************************************************************
	private static String baseClass;// = "com.michielvanderlee.annotationstest.BaseObject";
	
	private static Map<String, String> classNames = new HashMap<String, String>();
}
