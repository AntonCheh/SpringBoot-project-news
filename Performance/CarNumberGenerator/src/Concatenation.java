
public class Concatenation {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20_000; i++) {
            stringBuilder.append("some text some text some text");
        }
        System.out.println(stringBuilder);
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}

/* StringBuilder с методом append более эффективен с точки зрения производительности,
 особенно при многократной конкатенации строк.
 Это связано с тем, что StringBuilder изменяем,
 и он изменяет свое содержимое без создания новых объектов строки при каждом добавлении.
 Также StringBuilder предназначен специально для сборки строк и позволяет изменять содержимое
 без создания новых объектов.

Оператор +=:
 каждый раз создается новый объект String при каждом выполнении операции +=,
 поскольку строки в Java неизменяемы.
 Это может привести к большому количеству временных объектов и негативно сказаться на производительности
 при частом использовании.
 */
