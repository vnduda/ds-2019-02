package factory;

import java.lang.reflect.InvocationTargetException;

public class Aplicacao {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Aluno aluno = newInstance("factory.Aluno");
		Professor professor = newInstance("factory.Professor");
		System.out.println(aluno.atividade());
		System.out.println(professor.atividade());
	}
	
	public static <T> T newInstance(String className)
		 throws ClassNotFoundException, InstantiationException,
		       IllegalAccessException, InvocationTargetException,
		       NoSuchMethodException {
		   Class<?> classe = Class.forName(className);
		   	return (T) classe.getDeclaredConstructor().newInstance();
		}

}
