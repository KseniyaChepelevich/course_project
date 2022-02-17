

# Course_project

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

1.Запустить контейнеры командой 'docker-compose up -d'
1.Запустить приложение командой 'java -jar ./aqa-shop.jar'.
1.Запустить автотесты командой './gradlew clean test'. 
1.Для создания отчета Allure запустить команду './gradlew allureReport'.

## Лицензия

ОС - Windows 10 Pro x64
версия Java: 11.0.12

## [Документация к проекту]()
