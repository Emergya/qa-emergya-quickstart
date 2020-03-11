package runner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import org.apache.log4j.Logger;

import com.emergya.selenium.testSet.DefaultTestSet;

/***
 * This is an auxiliary class made to comply with the requirements of this framework. Basically, as we
 * are using TestNG, we need to send {@link DefaultTestSet} the method in which we are executing our test.
 * However, in our case in cucumber, we are not using different methods for each test. We are using, our
 * feature files and scenarios to define our tests and execute them. However, our API needs an object
 * of type Method as one of the parameters of the method DefaultTestSet.before(). So we need to create
 * a temporal class with a method for our current test execution. Also, this way the name of our test
 * in CrossBrowserTesting can be identified. We just need to provide the name of the class and the 
 * name of the method we want to generate and the constructor of this class will handle it.
 * In our case, is recommended to use the name of the feature as the name of the class and the scenario
 * as the name of the method
 * @author Antonio Pérez <aoviedo@emergya.com>
 *
 */

public class TestMethodGenerator {
	
	private Class <?> customClass = null;
	private String className = "";
	protected static Logger log = Logger.getLogger(DefaultTestSet.class);
	
	/**
	 * As you can see given a className and a methodName this constructor generates a java file
	 * in our tmp folder, compiles it and loads the class in runtime.
	 * @param className
	 * @param methodName
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 */
	public TestMethodGenerator(String className, String methodName) throws MalformedURLException, ClassNotFoundException {
		log.info("--------------- Class: " + TestMethodGenerator.class.getName() + " Method: " + TestMethodGenerator.class.getName()
                + " - Creating temporal test class ---------------");
		File sourceFile = this.generateSourceFile(className);
		this.className = sourceFile.getName().split("\\.")[0];
		String sourceCode = this.generateSourceCode(sourceFile, methodName);
		this.createSourceFile(sourceFile, sourceCode);
		File parentDirectory = this.compileClass(sourceFile);
		this.customClass = this.classLoader(parentDirectory, this.className);
		log.info("--------------- Class: " + TestMethodGenerator.class.getName() + " Method: " + TestMethodGenerator.class.getName()
                + " - Temporal test class created ---------------");
	}
	
	/**
	 * In this method we just generate a temporal file in folder /tmp/ that we will use to 
	 * create our class later.
	 * @param className
	 * @return
	 */
	public File generateSourceFile(String className) {
		File sourceFile = null;
		try {
			sourceFile = File.createTempFile(className, ".java");
			sourceFile.deleteOnExit();
		} catch (IOException e) {
			log.error("--------------- Class: " + TestMethodGenerator.class.getName() + " Method: " + "generateSourceFile"
                + " - Error ---------------");
			log.error(e);
			e.printStackTrace();
		}
        
        return sourceFile;
	}
	/**
	 * Here we generate the source code for our class. In our case, it will just contain the method
	 * we need to pass to DefaultTestSet.before in BasicStepsDefinitions.super
	 * @param sourceFile
	 * @param method
	 * @return
	 */
	public String generateSourceCode(File sourceFile, String method) {
		String classname = sourceFile.getName().split("\\.")[0];
        String sourceCode = "public class " + classname + "{ public void "+method+"() {}}";
        return sourceCode;
	}

	/**
	 * Here we finally create the source file that contains our class and method and we will later compile
	 * @param sourceFile
	 * @param sourceCode
	 */
	public void createSourceFile(File sourceFile, String sourceCode) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(sourceFile);
	        writer.write(sourceCode);
	        writer.close();
		} catch (IOException e) {
			log.error("--------------- Class: " + TestMethodGenerator.class.getName() + " Method: " + "createSourceFile"
	                + " - Error ---------------");
				log.error(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Here we compile the source file we generated previously so our custom made class is available later
	 * @param sourceFile
	 * @return
	 */
	public File compileClass(File sourceFile) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parentDirectory = sourceFile.getParentFile();
        try {
			fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
			Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
	        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
	        fileManager.close();
		} catch (IOException e) {
			log.error("--------------- Class: " + TestMethodGenerator.class.getName() + " Method: " + "compileClass"
	                + " - Error ---------------");
				log.error(e);
			e.printStackTrace();
		}
        return parentDirectory;
	}
	/**
	 * In this method we load our custom made class from its class file
	 * @param parentDirectory
	 * @param className
	 * @return
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 */
	public Class<?> classLoader(File parentDirectory, String className) throws MalformedURLException, ClassNotFoundException{
		 URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { parentDirectory.toURI().toURL() });
	        Class<?> customClass = classLoader.loadClass(className);
			return customClass;
	}
	/**
	 * This is the method we have to call to get the object of type Method that we will pass onto
	 * DefaultTestSet.before (in our code super.before in class BasicStepsDefinitions)
	 * @param methodName
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Method getTestMethod(String methodName) throws NoSuchMethodException, SecurityException {
		log.info("--------------- Class: " + TestMethodGenerator.class.getName() + " Method: " + "getTestMethod"
                + " - Getting temporal test method created for CrossBrowserTesting ---------------");
        Method testMethod = this.customClass.getDeclaredMethod(methodName);
        return testMethod;
        
	}
	

}
