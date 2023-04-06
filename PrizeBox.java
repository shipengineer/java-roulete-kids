package JavaMiddle;

import java.util.ArrayList;
import java.util.Random;

import JavaMiddle.Toys.Bullet;
import JavaMiddle.Toys.Dolly;
import JavaMiddle.Toys.HE;
import JavaMiddle.Toys.Rubics;
import JavaMiddle.Toys.Solder;
import JavaMiddle.Toys.Toy;

public class PrizeBox {
    private ArrayList<Toy> inside;

    public PrizeBox() {
        this.inside = new ArrayList<>();
    }

    public ArrayList<Toy> getInside() {
        return inside;
    }

    public void addToy(Toy toy) {

        if (toy.getQuantity() == 0) {

            System.out.println("Таких игрушек больше нет");
        } else {
            inside.add(toy);
            toy.downQuantity();
            System.out.println("Добавили" + toy.getTitle());
        }
    }

    public void removeItem(int index) {
        if (this.inside.size() == 0) {
            System.out.println("На витрине нет игрушек");
            return;
        }
        this.inside.remove(index);
    }

    public void clearBox() {
        this.inside.clear();
    }

    public String printBox() {
        String out = "В корзине лежит\n";
        int rows = Math.round(this.inside.size() / 3);
        for (int i = 0; i < rows; i++) {
            for (int j = i; j < rows; j++) {
                out = out + this.inside.get(j + i).getTitle() + "|";
            }
            out = out + "\n";
        }

        return out;
    }

    public int dropPrize() {
        Random random = new Random();
        Integer[] firstDrop = new Integer[(int) (1 + this.inside.size() / 10)];
        // первый выбор элементов
        if (this.inside.size() == 0) {
            return -1;
        }
        for (int i = 0; i < firstDrop.length; i++) {
            if (firstDrop.length > 1) {
                firstDrop[i] = random.nextInt(0, (this.inside.size() - 1));
            } else {
                firstDrop[0] = random.nextInt(this.inside.size());
            }

        }
        // нахождение наибольшего веса
        int maxIndex = firstDrop[0];
        int maxValue = this.inside.get(maxIndex).getChance();
        this.inside.get(firstDrop[0]).getChance();
        for (int i = 0; i < firstDrop.length; i++) {
            if (this.inside.get(firstDrop[i]).getChance() > maxValue) {
                maxValue = this.inside.get(firstDrop[i]).getChance();
                maxIndex = firstDrop[i];
            } else if (this.inside.get(firstDrop[i]).getChance() == maxValue) {
                if (random.nextBoolean()) {
                    maxIndex = firstDrop[i];
                    maxValue = this.inside.get(firstDrop[i]).getChance();
                }
            }
        }

        return maxIndex;
    }

    public void FillBox(Bullet bullet, Dolly dolly, Solder solder, Rubics rubics, HE he) {
        int randToy;
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            randToy = random.nextInt(1, 5);
            switch (randToy) {
                case 1:
                    this.inside.add(bullet);
                    bullet.downQuantity();
                case 2:
                    this.inside.add(dolly);
                    dolly.downQuantity();
                case 3:
                    this.inside.add(solder);
                    solder.downQuantity();
                case 4:
                    this.inside.add(rubics);
                    rubics.downQuantity();
                case 5:
                    this.inside.add(he);
                    he.downQuantity();

            }
        }
        System.out.println(this.printBox());
    }

}