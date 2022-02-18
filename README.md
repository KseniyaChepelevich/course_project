

# Курсовой проект по модулю «Автоматизация тестирования» для профессии «Инженер по тестированию»

Курсовой проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Начало работы

1.Запустить Idea.
1.В терминале Idea ввести команду "clone https://github.com/KseniyaChepelevich/course_project.git".
1.Дождаться окончания закрузки и индексации файлов.
1.Выполнить шаги описанные в разделе "Установка и запуск."

### Prerequisites

Для работы с проектом необходимо установить:

IntelliJ IDEA Ultimate
Docker Desktop
Расширения для IntelliJ IDEA:
Docker
DataBaseTool and Sql
Node.js

### Установка и запуск

Для запуска тестов необходимо:

1.Запустить контейнеры командой 'docker-compose up -d'.  
1.Создать соединение с базой данных Database/new/Data Sourse/MySQL. Создать соединение с  параметрами: Host - localhost, port - 3306. user - aqa, password - mypass, Database - aqa-shop.  
1.Запустить приложение командой 'java -jar ./aqa-shop.jar'.  
1.Обновить базу данных Refresh (Ctrl + F5).   
1.Запустить автотесты командой './gradlew clean test'.   
1.Для создания отчета Allure запустить команду './gradlew allureReport'.

## Лицензия

ОС - Windows 10 Pro x64
версия Java: 11.0.12

## [Документация к проекту](https://github.com/KseniyaChepelevich/course_project/tree/master/docs)
