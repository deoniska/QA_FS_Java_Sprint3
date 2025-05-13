Чтобы скачать репозиторий **QA_FS_Java_Sprint3** с GitHub в **IntelliJ IDEA**, выполните следующие шаги:

---

### **1. Откройте IntelliJ IDEA**

Запустите IDE. Если у вас уже открыт проект, перейдите в:

- **File → New → Project from Version Control...**

Если IDEA только запущена:

- Нажмите **Get from VCS** на стартовом экране.

---

### **2. Вставьте URL репозитория**

В открывшемся окне:

1. Выберите **Git** в качестве системы контроля версий.
2. В поле **URL** вставьте ссылку на репозиторий (например):
   ```
   https://github.com/ваш-логин/QA_FS_Java_Sprint3.git
   ```  
3. Укажите **директорию**, куда сохранить проект (например, `C:\Projects\QA_FS_Java_Sprint3`).
4. Нажмите **Clone**.

---

### **3. Откройте проект в IDEA**

После клонирования IDEA предложит:

- **Trust Project** (если проект из ненадежного источника, нажмите "Trust").
- **Open in New Window** (рекомендуется).

---


Теперь проект готов к работе! 🚀

--- 

### **Пишем тесты**

**1. Добавляем библиотеку Junit в POM файл.**  
- Вариант 1 - нажать  ```ctrl + N для Windows``` и ```cmd + N для MacOs``` выбрать ```test```  
При нажатии на ```Fix``` последняя версия JUnit будет автоматически добавлена в ```Pom.xml```  
- Вариант 2 - напрямую добавить зависимость в ```Pom.xml```  
пример:

```xml
 <dependency>
 <groupId>junit</groupId>
 <artifactId>junit</artifactId>
 <version>4.13.1</version>
 <scope>test</scope>
 </dependency>
```
**2. Пишем наши первые тесты.**  
```java
package ru.yandex.prakticum;

import org.junit.Test;
import static org.junit.Assert.*;

public class SpeedLightsTest {

    @Test// аннотация, после этого метод становится тестом
    public void testShowLightGreenWhenSpeedBelowLimit() {
        SpeedLights speedLights = new SpeedLights();
        assertEquals("green", speedLights.showLight(50));
    }

    @Test
    public void testShowLightGreenWhenSpeedAtLimit() {
        SpeedLights speedLights = new SpeedLights();
        assertEquals("green", speedLights.showLight(59));
    }

    @Test
    public void testShowLightYellowWhenSpeedAtLowerBoundary() {
        SpeedLights speedLights = new SpeedLights();
        assertEquals("yellow", speedLights.showLight(60));
    }

    @Test
    public void testShowLightYellowWhenSpeedAtUpperBoundary() {
        SpeedLights speedLights = new SpeedLights();
        assertEquals("yellow", speedLights.showLight(80));  
    }

    @Test
    public void testShowLightRedWhenSpeedAtLowerBoundary() {
        SpeedLights speedLights = new SpeedLights();
        assertEquals("red", speedLights.showLight(81));  
    }

    @Test
    public void testShowLightRedWhenSpeedAboveLimit() {
        SpeedLights speedLights = new SpeedLights();
        assertEquals("red", speedLights.showLight(100));
    }
}
```
**2. Убираем дублирование и показываем аннотация @Before**
```java
private  SpeedLights speedLights;

    @Before
    public void setUp() {
        speedLights = new SpeedLights();
    }
```
**3. Пишем первый параметризованный тест**
```java
package ru.yandex.prakticum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SpeedLightsParameterizedTest {

    private int speed;
    private String expectedColor;
    private SpeedLights speedLights;

    public SpeedLightsParameterizedTest(int speed, String expectedColor) {
        this.speed = speed;
        this.expectedColor = expectedColor;
    }

    @Before
    public void setUp() {
        speedLights = new SpeedLights();
    }

    @Parameterized.Parameters()
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {50, "green"},   // Ниже лимита
            {59, "green"},   // Граница зеленой зоны
            {60, "yellow"},  // Начало желтой зоны
            {70, "yellow"},  // Середина желтой зоны
            {80, "yellow"}, // Граница желтой зоны
            {81, "red"},     // Начало красной зоны
            {100, "red"}    // Выше лимита
        });
    }

    @Test
    public void testShowLightWithParameters() {
        assertEquals(expectedColor, speedLights.showLight(speed));
    }
}
```

**4. Добавляем наименование к параметру для отображения** 

```java
@Parameterized.Parameters(name = "Speed: {0} km/h -> Expected color: {1}")
```

**5. Запускаем тесты** 
Видим, что наши тесты обнаруживают дефект (заложенный изначально). Запускаем Debug при выполнении теста и по шагам проходим до места проблемы.  
Исправляем код 
```java
public String showLight(int currentSpeed) {
        if (currentSpeed < 60) {
            return "green";
        } else if (currentSpeed <= 80) {  // Изменено с <80 на <=80
            return "yellow";
        } else {
            return "red";
        }
    }
```