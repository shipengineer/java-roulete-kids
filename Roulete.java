package JavaMiddle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import JavaMiddle.Toys.Bullet;
import JavaMiddle.Toys.Dolly;
import JavaMiddle.Toys.HE;
import JavaMiddle.Toys.Rubics;
import JavaMiddle.Toys.Solder;

/**
 * Roulete
 */
public class Roulete {

    public static void main(String[] args) throws IOException {
        try (Scanner readScanner = new Scanner(System.in)) {

            PrizeBox box = new PrizeBox();
            boolean active = true;
            box.FillBox();
            System.out.println("Добро пожаловать в нашу игру");
            while (active) {
                System.out.println("______________________________________________________________");

                System.out.println(
                        "Чего бы вам хотелось\n1-Посмотреть витрину\n2-Обновить витрину\n3-Собрать свою витрину\n4-Выйграть приз\n5-Выйти\n");
                System.out.println("Ваша команда:");
                int command = readScanner.nextInt();
                switch (command) {
                    case 1:
                        System.out.println(box.printBox());
                        break;
                    case 2:
                        box.clearBox();
                        box.FillBox();
                        break;
                    case 3:
                        box.clearBox();
                        Boolean edit = true;
                        while (edit) {
                            System.out.println("Ваша витрина:");
                            System.out.println(box.printBox());
                            System.out.println("______________________________________________________________");
                            System.out.println(
                                    "Что хотите добавить\n1-Пулю\n2-Куклу\n3-Солдатика\n4-Кубик Рубика\n5-Вызрыватку\n6-Закончить набор игрушек");
                            System.out.println("Ваша команда:");
                            int whatToAdd = readScanner.nextInt();
                            switch (whatToAdd) {
                                case 1:
                                    box.addToy(new Bullet());
                                    break;
                                case 2:
                                    box.addToy(new Dolly());
                                    break;
                                case 3:
                                    box.addToy(new Solder());
                                    break;
                                case 4:
                                    box.addToy(new Rubics());
                                    break;
                                case 5:
                                    box.addToy(new HE());
                                    break;
                                case 6:
                                    edit = false;
                                    System.out.println("Ваша витрина:");
                                    System.out.println(box.printBox());
                                    break;
                                default:
                                    System.out.println("Непонятная команда");
                                    break;
                            }

                        }
                    case 4:

                        int prizeIndex = box.dropPrize();
                        if (prizeIndex == -1) {
                            System.out.println("игрушек на витрине нет");
                            break;
                        }
                        try (FileWriter writer = new FileWriter("./YourPrize.txt")) {
                            writer.write(box.getInside().get(prizeIndex).getTitle());
                        }
                        System.out.println("Поздравляю! вы выйграли " + box.getInside().get(prizeIndex).getTitle());
                        System.out.println("Он уже лежит в вашем файлике");
                        box.removeItem(prizeIndex);
                        break;
                    case 5:
                        System.out.println("Всего доброго! До свидания!");
                        active = false;
                        break;

                    default:
                        System.out.println("Неверная команда, повторите, пожалуйста");
                        break;
                }

            }
        }

    }
}
