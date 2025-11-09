package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws Exception {
        File configFile = new File("config.txt");
        if (!configFile.exists()) {
            throw new IllegalStateException("Configuration file 'config.txt' not found in project root");
        }

        try (Scanner scanner = new Scanner(configFile)) {
            if (!scanner.hasNextLine()) {
                throw new IllegalStateException("DAO class name is missing in config.txt");
            }
            String daoClassName = scanner.nextLine().trim();

            Class<?> cDao = Class.forName(daoClassName);
            IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

            if (!scanner.hasNextLine()) {
                throw new IllegalStateException("Metier class name is missing in config.txt");
            }
            String metierClassName = scanner.nextLine().trim();

            Class<?> cMetier = Class.forName(metierClassName);
            IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

            Method setDaoMethod = cMetier.getMethod("setDao", IDao.class);
            setDaoMethod.invoke(metier, dao);

            System.out.println("Résultat = " + metier.calcul());
        }
    }
}
