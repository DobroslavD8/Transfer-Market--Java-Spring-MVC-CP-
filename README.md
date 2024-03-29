# Тема на курсовата работа: Списък с играчи на футболния трансферен пазар (MVC)

## Стъпки по създаването на курсовата работа:

⦁	Създаване на база данни, посредством изпълнение на командата по-долу в MySQL Workbench:
CREATE TABLE IF NOT EXISTS `tbl_footballers`(
`footballer_id` int(11) NOT NULL,
`footballer_firstname` varchar(15) NOT NULL,
`footballer_lastname` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARTSET=latin1 ROW_FORMAT=COMPACT;
⦁	Създаването на проекта става през start.spring.io, където генерирам базовите неща, използвани по-нататък в разработката (Maven проект, Java lang, Spring boot, Description, Package Name, Packaging, Java version & др.). Добавям и Dependency-та за Spring Web, Spring Data JPA, MySQL Driver и Thymeleaf, след което отварям генерирания файл през Netbeans и правя нужните корекции в pom.xml файла, както и в application.properties;

⦁	Създавам "model" пакет, който съдържа полетата на Footballer, както и getter-и & setter-и към тях;

⦁	Създавам "repository" пакет, в който има FootballerRepository файл, правещ връзката между модела и базата данни, посредством заявка;

⦁	Създавам "service" пакет, в който има FootballerService файл. В него е декларирана връзката с репозиторито и са описани CRUD операциите, предоставящи възможност за преглеждане, добавяне, изтриване и редактиране;

⦁	Създавам "controller" пакет, в който има FootballerController файл. В него се определя rooting-а и действията, които се предприемат при попадане на дадена страница, както и къде да бъде препратен потребителят, когато достъпи страницата или попълни необходимите полета и натисне бутон; В този клас е имплементиран и филтърът, който позволява да се отсява от съдържанието по таблицата по различен критерии (напр. ключова дума);


За да се визуализира всичко дотук, създавам 3 view-та (html страници) - index, insert и update.
⦁	Index.html представлява приветстващо заглавие (Welcome), бутон за излизане от системата (Logout), линк за преминаване към формата за добавяне на играч, търсачка (филтър) и таблица, изобразяваща наличните записи на футболисти към този момент в базата данни;

⦁	Insert.html страницата е със същия хедър, като вместо линк към страницата за добавяне на футболист, има линк, водещ към главната страница (списъка с футболисти). На тази страница има 2 input полета (с подходящи title-и), както и бутон за добавяне на футболисти, който изпраща данните към БД. Полетата разполагат и с валидация. Те са задължителни за попълване, а условията, които трябва да са изпълнени, за да бъде завършено добавянето, са да бъдат използвани само букви, както и символите да са между 1 и 20.

⦁	Update.html страницата има същия хедър и линк, който препраща към главната страница. Тук се визуализира вече съществуващ запис, срещу който в таблицата сме натиснали бутона за ъпдейт. Ако желаем да променим запис, то правим промените, а бутонът "Update" прави заявка до базата и променя съдържанието там. Полетата също разполагат с валидация, идентична на тази от страницата с добавяне на нов футболист.
 

Логването в системата се извършва чрез предоставено от Spring dependency, което използва име по подразбиране (user) и генерирана парола в output-а на проекта.


## Описание на основната функционалност на системата

### FootballController.java (Controller)
⦁	В контролерът е декларирана инстанция на FootballerService, която позволява да се използват CRUD заявките. В метода viewIndexPage с параметри model и keyword се взимат всички записи, които са попаднали в списъка Footballer (footballerList). Тук е имплементацията на филтъра, който преглежда дали в записите съществува думата, която търсим и ако съществува, то да се извеждат резултатите само съдържащи нея, а в противен случай - всички;

⦁	new_add пътят сочи към страницата Insert.html, където получаваме за ползване полетата от нея страница;

⦁	save_footballer методът изпраща данните към БД и ни връща обратно на страницата;

⦁	/edit/{id} показва страницата за редакция, според уникалното id на записа, който искаме да редактираме;

⦁	/delete/{id} отново чрез уникалното id взимаме записа и посредством delete метода от сървисите го изтриваме от БД.

### Footballer.java (Model)
В класа, описващ модела казваме, че той се използва от таблицата "tbl_footballers" от нашата база данни, след което декларираме полетата (id, firstname & lastname) в нашия клас. За всяко от тях добавяме нужните getter-и и setter-и, за да може да ги използваме впоследствие.

### FootballerRepository.java (Repository)
В репозитори файла се случва връзката между проекта (модела) и базата данни.
За филтрацията се използва @Query с параметър SQL заявката към БД (в случая select * from tbl_footballers e where e.footballer_firstname like %:keyword% or e.footballer_lastname like %:keyword%"), която извлича само записи, които отговарят на определен критерий (в случая съдържат ключовата дума, която сме въвели в input-a).

### FootballService.java (Service)
В сървисите се имплементира инстанция на репозиторито, която се ползва във всяка една от CRUD заявките, както и за имплементиране на филтрацията.


## Примерна разходка в сайта:

1. Стартираме програмата.
2. Копираме генерираната парола от Output полето.
3. На логин страницата използваме потр. име по подразбиране (user) и генерираната парола, след което се логваме.
4. Виждаме главната страница и от нея имаме възможността да добавим нов запис, да изтрием или редактираме вече съществуващ.
4.1. За да добавим нов запис натискаме на линка "Add football player to Transfer List" и съответно попълваме нужните полета.
4.2. За да редактираме съществуващ запис натискаме на бутона "Edit Player" срещу желания запис, променяме желаното поле и запаметяваме промените.
4.3. Изтриването става, когато натиснем бутона "Delete from TF" срещу желания запис.