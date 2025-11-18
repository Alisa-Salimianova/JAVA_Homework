import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        LinkedStack stack = new LinkedStack();

        System.out.println(stack); // EMPTY

        stack.push(5);
        System.out.println(stack); // 5

        stack.push(15);
        System.out.println(stack); // 15 -> 5

        stack.push(25);
        System.out.println(stack); // 25 -> 15 -> 5

        System.out.println(stack.pop()); // 25
        System.out.println(stack); // 15 -> 5

        System.out.println(stack.pop()); // 15
        System.out.println(stack); // 5

        System.out.println(stack.pop()); // 5
        System.out.println(stack); // EMPTY
    }

}

class Node {
    private int value;
    private Node prev; // ссылка на обёртку предыдущего элемента

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getPrev() {
        return prev;
    }

    /** Связывание узла с предыдущим, т.е. выставление узлу какой узел является к нему предыдущим */
    public void setPrev(Node prev) {
        this.prev = prev;
    }
}

class LinkedStack {
    private Node tail; // ссылка на последний добавленный узел (обёртку)
    private int size; // размер стека, т.е. количество элементов в нём

    public void push(int value) {
        Node node = new Node(value); // создаём новый узел
        if (tail != null) { // если в стеке уже есть элементы
            node.setPrev(tail); // связываем новый узел с последним
        }
        tail = node; // назначаем новый узел последним узлом
        size++; // увеличиваем счётчик элементов
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        int value = tail.getValue(); // берём значение из последнего узла
        tail = tail.getPrev(); // назначаем предыдущий узел последним
        size--; // уменьшаем счётчик элементов

        return value;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "EMPTY";
        }

        StringBuilder sb = new StringBuilder();
        Node current = tail;

        // Проходим по всем элементам стека
        while (current != null) {
            sb.append(current.getValue());
            if (current.getPrev() != null) {
                sb.append(" -> ");
            }
            current = current.getPrev();
        }

        return sb.toString();
    }
}