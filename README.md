## README

**Main:**

In Main am parsat JSON-urile de la input in clase aparte (Parser), dupa care
s-au apelat metoda pentru runda 0, si alta pentru rundele anuale.
Am creat objectMapper-ul ca sa se creeze fisierul cu outputs.

**Parser:**

Aici se parseaza JSON-urile.

**InitialData:**

Gettere si Settere pentru lista de copii initiala si lista de cadouri pentru
runda 0;

**OutData:**

Clasa asta ne ajuta ca sa scriem in fieserele de output ArrayList-ul de copii.

**Years:**

Aici se scriu ArrayList-ul de copii pentru un an anume, care va fi apoi scris
in OutData.

**Flow:**

    - roundZero() : 
    initializam ArrayList-ul children si santaGifts cu cu cel din initialData,
    apoi apelam functia _checkForAdults_ (daca age <= 18, se adauga intr-un nou
    ArrayList, care devine apoi this.children).
    Se seteaza niceScoreHistory pentru copii in metoda _setNiceScoreList_
    (pentru aceasta runda, primul element din array devine niceScore-ul
    initial).
    Se calculeaza dupa formula in _calcBudgetUnit_, apoi pentru fiecare copil
    se va face _calcAssignedBudget_ (modifica bugetul in dependenta de
    avgScore al copilului si budgetUnit).
    In elfBudget se face accept la toti elfii, insa in visitor se verifica daca
    elf-ul copilului coincide cu visitor, dupa care se fac modificarile
    pe buget.
    Pentru runda zero, strategia de asignare a cadourilor e dupa ID, deci i-am
    dat parametrul "id" metodei _strategyChoice_. In metoda sunt implementate
    comparatoare pentru fiecare tip de strategie in dependenta de input, care
    rearanjeaza copiii din lista in baza strategiei.
    Dupa rearanjare, se repartizeaza cadourile in metoda _giftsForKids_,
    luandu-se in vedere preferintele copilului, cantitatea de cadouri, bugetul
    asignat si pretul cadourilor. Cadoul cel mai potrivit din fiecare categorie
    e adaugat in ArrayList-ul de receivedGifts pentru copil.
    Dupa ce se repartizeaza cadourile, se face accept pentru elful yellow, care
    daca este asignat unui copil, verifica daca nu a primit cadou si incearca
    sa-i dea unul din categoria sa preferata.
    Se adauga in arrayList-ul pentru output lista de copiii cu toate cadourile
    primite.

    - annualRounds():
    are acelasi algoritm ca si runda zero. se itereaza prin numarul de ani si
    se implementeaza aceleasi metode ca si in prima runda, cu mici modificari.
    se mareste varsta tuturor copiilor (updateAge in Child).
    se adauga in lista de copii noii copii de la AnnualChanges pentru acel an,
    dupa care se verifica daca nu sunt adulti.
    in notifyChildren se verifica copiii cu acelasi ID ca si ID-ul copiilor din
    childrenUpdates - > se face update la acestia.
    update-ul consta in adaugarea de niceScore in niceScoreHistory, schimbarea
    preferintelor (adaugarea la inceputul listei de preferinte), si obtinerea
    unui alt elf. Dupa ce se face update-ul, se recalculeaza averageScore-ul in
    cazul in care a fost modificat NiceScoreHistory.
    se modifica lista de cadouri ( se face add la lista de cadouri la cadourile
    mosului cu cele din annualChanges)
    
**Child:**

In clasa child sunt mai multe metode pentru copii, si niste comparatoare.
Am mentionat mai sus functiile de Update, UpdateAge. Am si metoda de
calcAvgScore care calculeaza averageScore-ul unui copil in dependenta de
varsta, si niceScoreBonus. removeDups sterge din lista de preferinte ce se
repeta (a fost un caz intr-un test). copyChild copiaza toate campurile
copilului necesare la output intr-un alt obiect (deep copy), ca sa nu
se modifice lista initiala.

Comparatoarele din Child sunt pentru ID (sorteaza crescator copiii),
cityScore (sorteaza dupa averageScore pe oras), cityName(sorteaza alfabetic
orasele).


**_Design Patterns folosite:_**

- _Visitor_ (ElfVisitor): Child accepta un ElfVisitor. In dependenta de ce elf are
acesta la input, se fac diferite operatii de modificare a bugetului per copil.
Elful Yellow este exceptia, care viziteaza copilul si ii adauga un cadou, in
cazul in care acesta nu a primit niciunul.

ElfiWhite, ElfBlack, ElfPink, ElfYellow - toti mostenesc ElfVisitor.

                    -------|    Flow     |-------
                   |                            |
                ---------                  ------------
                 Child                      ElfVisitor 
                accept()                       visit   
               -----------                 ------------
                                                |
                                          -------------
                                            ElfWhite  
                                           visit(child)
                                          -------------
                                                |
                                          -------------
                                            ElfBlack  
                                           visit(child)
                                          -------------
                                                |
                                          -------------
                                            ElfPink   
                                           visit(child)
                                          -------------
                                                |
                                          -------------
                                            ElfYellow 
                                           visit(child)
                                          -------------

- _Observer_ (Child): Copiii sunt observeri, iar in Flow sunt notificati sa faca
update, ca sa isi modifice parametrii.
                                
                                Flow notify
                                  
                      |       |               |        |
                      |       |               |        |
                child()    child()        child()    child()

Fiecare copil face update, primind un parametru necesar in metoda (in cazul
nostru, un alt copil, de la care o sa ii preia unele campuri, precum
niceScore, giftPreferences).