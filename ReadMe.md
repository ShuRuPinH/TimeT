# TimeT
_____
## Общее описание

Приложение отправит СМС на нужный номер, в назначенное время, с возможностью нескольких повторов (до 10) через установленный интервал в минутах (шаг 0,5 мин).
Программа создана в рамках курсового проекта, поэтому  в реализации используются:
- Maven
- WebSockets
- Servlets
* Фильтры, "мэппинг" как контроль доступа к ресурсам
* JSP, JSTL, HTML и пр.
* HttpSession для проверки авторизации
- Многопоточность (асинхронная)
- JavaScript
- реализация некоторых паттернов программирования: синглтон, билдер.
- и другой функционал согласно программе обучения...

## Принцип работы и структура

Хранение данных организовано внутренней базой данных, она в свою очередь, в файлах, сереализованных JSON.
[/main/java/classes/DataBaseDir/](https://github.com/ShuRuPinH/TimeT/tree/master/src/main/java/classes/DataBaseDir)

Сервер отправки сообщений, реализован как Android приложение, которое имеет свой внешний IP-адрес, и про получении шифрованного Base64 запроса, отправляет СМС через аппаратуру смартфона. _/код не представлен/_

Большинство сервлеты и фильтры обеспечивают, всю необходимую работы интерфейса.
Для реализации некоторых функций, таких как: проверка вводимых паролей на соответствие требованиям, нотификация и дешифровка, — использован JavaScript.

Отдельный класс SMScore, обеспечивает  формирования “расписания” сообщений, и их отправку, реализован как
синглтон с непрерывным таймером, который:
- каждые 10 секунд проверяет и  каждую минуту актуализирует “расписание”,
- проверяет лимиты сообщений по юзеру по сервису,
- отправляет подходящие по времени напоминания, запросом на СМС сервер
- формирует отчет по отправке и сохраняет  историю.

####Дополнительные классы:
- **Loger** - синглтон через enum, записывает 2 лог файла, по действиям и по авторизациям
- **Setup** - сервлет, создает системные папки на сервере размещения
- **Verificator** - сервлет, генерирует ссылку для восстановления пароля, хранить информация и проверяет валидность запроса,
- **Emailer** - класс отправляющий е-мейлы пользователям
- **Archiver** - класс которых, архивирует файлы истории удаленных пользователей и сохраняет zip-архив














