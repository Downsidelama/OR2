# Osztott rendszerek kidolgozás

# Bevezetés

## Definíció: Elosztott rendszer

Az elosztott rendszer önálló számítógépek olyan összessége, amely kezeloi számára egyetlen koherens rendszernek tűnik.

## Az elosztott rendszer célja

-   Távoli eroforrások elérhetővé tétele
-   Átlátszóság (distribution transparency)
-   Nyitottság (openness)
-   Skálázhatóság (scalability)

## Az átlátszóság mértéke

-   A teljes átlátszóságra törekvés általában túl eros:
    -   A felhasználók különbözo kontinenseken tartózkodhatnak
    -   A hálózatok és az egyes gépek meghibásodásának teljes elfedése elméletileg és gyakorlatilag is lehetetlen
        -   Nem lehet eldönteni, hogy a szerver csak lassan válaszol vagy meghibásodott
        -   Távolról nem megállapítható, hogy a szerver feldolgozta-e a kérésünket, mielott összeomlott
    -   A nagymértékű átlátszóság a hatékonyság rovására megy, de a késleltetést is el szeretnénk rejteni
        -   Ilyen feladat lehet a webes gyorsítótárak (cache-ek) tökéletesen frissen tartása
        -   Másik példa: minden változás azonnal lemezre írása nagymértékű hibatűréshez

## Elosztott rendszerek nyitottsága

### Nyitott elosztott rendszer

A rendszer képes más nyitott rendszerek számára szolgáltatásokat nyújtani, és azok szolgáltatásait igénybe venni:

-   A rendszerek jól definiált interfészekkel rendelkeznek
-   Az alkalmazások hordozhatóságát (portability) minél inkább támogatják
-   Könnyen elérheto a rendszerek együttműködése (interoperability)

A nyitottság elérése

A nyitott elosztott rendszer legyen könnyen alkalmazható heterogén környezetben, azaz különböző

-   hardvereken,
-   platformokon,
-   programozási nyelveken.

### A nyitottság implementálása

-   Fontos, hogy a rendszer könnyen cserélheto részekből álljon
-   Belso interfészek használata, nem egyetlen monolitikus rendszer
-   A rendszernek minél jobban paraméterezhetonek kell lennie
-   Egyetlen komponens megváltoztatása/cseréje lehetoleg minél kevésbé hasson a rendszer más részeire

### Átméretezhetoség

Ha egy „kis" rendszer megno, az sokfajta kihívást jelenthet. Több különböző jellege is megnohet a rendszernek:

-   méret szerinti átméretezhetoség: több felhasználó és/vagy folyamat van a rendszerben
-   földrajzi átméretezhetoség: a rendszert nagyobb területrol veszik igénybe, pl. egyetemen belüli felhasználás→világméretű felhasználóbázis
-   adminisztrációs átméretezhetoség : biztonsági, karbantartási, együttműködési kérdések merülnek fel, ha új adminisztrációs tartományok kerülnek a rendszerbe

## Technikák az átméretezhetoség megvalósítására

### A kommunikációs késleltetés elfedése

A válaszra várás közben más tevékenység végzése:

-   Aszinkron kommunikáció használata
-   A beérkezo választ külön kezelő dolgozza fel
-   Probléma: nem minden alkalmazás ültetheto át ilyen megközelítésre

### Elosztás

Az adatokat és a számításokat több számítógép tárolja/végzi:

-   A számítások egy részét a kliensoldal végzi (Java appletek)
-   Decentralizált elnevezési rendszerek (DNS)
-   Decentralizált információs rendszerek (WWW)

### Replikáció/cache-elés

Több számítógép tárolja egy adat másolatait:

-   Replikált fájlszerverek és adatbázisok
-   Tükrözött weboldalak
-   Weboldalak cache-elése (böngészokben, proxy szervereken)
-   Fájlok cache-elése (a szerver- és kliensoldalon)

## Átméretezhetoség – a probléma

### Megjegyzés

Az átméretezhetoség könnyen elérhető, de ára van:

-   Több másolat fenntartása (cache vagy replika) inkonzisztenciához vezet: ha módosítunk egy másolatot, az eltér a többitol.
-   A másolatok konzisztensen tartásához globális szinkronizációra van szükség minden egyes változtatás után.
-   A globális szinkronizáció viszont rosszul skálázható nagy rendszerekre.

## Következmény

Ha feladjuk a globális szinkronizációt, akkor kénytelenek vagyunk bizonyos fokú inkonzisztenciát elviselni a rendszerünkben. Az, hogy ez milyen mértékben elfogadható, rendszerfüggő.

## Elosztott rendszerek fejlesztése: hibalehetoségek

### Megjegyzés

Az elosztott rendszer környezetérol kényelmes lehet feltételezni, hogy megbízható. Ha ez tévesnek bizonyul, az a rendszer újratervezéséhez vezethet. Néhány ilyen feltételezés:

-   a hálózat hibamentes
-   a hálózat biztonságos
-   a hálózat homogén
-   a hálózati topológia nem változik
-   a kommunikációnak nincsen idoigénye
-   a sávszélesség korlátlan
-   a kommunikációnak nincsen költsége
-   csak egy adminisztrátor van

## Elosztott rendszerek fajtái

-   Elosztott számítási rendszerek
    -   grid
    -   cluster
    -   cloud
-   Elosztott információs rendszerek
-   Elosztott átható (pervasive, ubiquitous) rendszerek

## Elosztott számítási rendszerek

### Számítási rendszer

Sok elosztott rendszer célja számítások végzése nagy teljesítménnyel.

### Cluster (fürt)

Lokális hálózatra kapcsolt számítógépek összessége.

-   Homogén: ugyanaz az operációs rendszer, hardveresen nem vagy csak alig térnek el
-   A vezérlés központosítva van, általában egyetlen gépre

### Grid (rács)

Több gép, kevésbé egységesek:

-   Heterogén architektúra
-   Átívelhet több szervezeti egységen
-   Nagyméretű hálózatokra terjedhet ki

### Felho (cloud)

Többrétegű architektúra.

#### Négy különbözo réteg:

-   Hardver: Processzorok, útválasztók (routerek), áramforrások, hűtoberendezések. A felhasználók közvetlenül nem látják.
-   Infrastruktúra: Virtuális hardvert tesz elérhetové: szerver, adattároló, hálózati kapcsolat, számítási kapacitás lefoglalása és kezelése.
-   Platform: Magasabb szintű absztrakciókat biztosít. Pl. az Amazon S3 társzolgáltatás különbözo fájlműveleteket biztosít; a felhasználónak vödrei (bucket) vannak, ebbe feltölthet, letölthet stb. fájlokat egy API segítségével.
-   Alkalmazás: A végfelhasználónak szánt, jellemzoen grafikus felületű alkalmazások.

## Elosztott információs rendszerek

### Definíció

Sok elosztott rendszer elsodleges célja adatok kezelése, illetve meglevo ilyen rendszerek elérése. Példa: tranzakciókezelo rendszerek

## Elosztott információs rendszerek: tranzakciók

Modell

A tranzakció adatok összességén (adatbázis, objektumok vagy más adattár) végzett művelet (lehetnek részműveletei), melynek az alábbi tulajdonságai vannak. A kezdobetűk rövidítéséből ACID-nek szokás nevezni a követelményrendszert

-   Atomicity (atomosság): Vagy a tranzakció minden eleme sikerrel végrehajtódik, vagy egyik sem.
-   Consistency (konzisztencia): A tranzakció végével konzisztens állapot kell, hogy fent álljon. (közben elofordulhat inkonzisztencia)
-   Isolation (elkülöníthetoség): Minden tranzakciónak úgy kell végbemennie, mintha az lenne az egyetlen aktív tranzakció, vagyis elkülöníthetoek legyenek egymástól és ne befolyásolják egymást semmilyen módon.
-   Durability (tartósság): A tranzakció által végrehajtott módosításokat tartós adattárolóra kell írni, hogy esetleges rendszerhiba esetén visszaállíthatóak legyenek.

### Tranzakciófeldolgozó monitor

A tranzakciókat sokszor több szerver hajtja végre. Ezeket egy TP monitor vezérli.

## Alkalmazásintegráció nagy rendszerekben

A TP monitor nem választja el az alkalmazásokat az adatbázisoktól. Továbbá az alkalmazásoknak egymással is kommunikálniuk kell.

-   Távoli eljáráshívás (Remote Procedure Call, RPC)
-   Üzenetorientált köztesréteg (Message-Oriented Middleware, MOM)

## Elosztott átható rendszerek

Sok modern elosztott rendszer kicsi, mobil elemekbol áll.

### Néhány jellemző

-   A környezet megváltozhat: A rendszernek ezt követnie kell.
-   Ad hoc szervezodés: A rendszer komponenseit nagyon különböző módokon használhatják a felhasználók. Ezért a rendszernek könnyen konfigurálhatónak kell lennie.
-   Megosztott szolgáltatások: Mivel a rendszer nagyon változékony, az adatoknak könnyen kell áramlaniuk. Ennek elosegítésére a rendszer elemei általában nagyon egyszerű szerkezetűek.

## Érzékelohálózatok

Az érzékeloket tartalmazó csúcsok

-   sok van belolük (nagyságrendileg 10-1000 darab)
-   egyszerűek (kevés memória, számítási és kommunikációs kapacitás)
-   sokszor elemrol működnek, vagy áramforrás sem szükséges hozzájuk

# Architektúrák

-   Architektúrafajták
-   Szoftverarchitektúrák
-   Architektúrák és köztesréteg
-   Az elosztott rendszerek önszervezése

## Architektúrafajták

A rendszer elemeit szervezzük logikai szerepük szerint különböző komponensekbe, és ezeket osszuk el a rendszer gépein.

![](tobbretegu.PNG)

(a) A többrétegű megközelítés kliens-szerver rendszerek esetén jól működik

(b) Itt a komponensek (objektumok) összetettebb struktúrában kommunikálnak, mindegyik közvetlenül küld üzeneteket a többieknek

A komponensek közötti kommunikáció történhet közvetlen kapcsolat nélkül („anonim"), illetve egyidejűség nélkül („aszinkron").

![](aszinkron.png)

(a) Publish/subscribe modell (térben független)

(b) Megosztott, perzisztens adattár (térben és idoben független)

## Központosított architektúrák

### Egyszerű kliens–szerver modell

Jellemzoi:
egyes folyamatok szolgáltatásokat ajánlanak ki (ezek a szerverek) más folyamatok ezeket a szolgáltatásokat szeretnék használni (ezek a kliensek) a kliensek és a szerverek különbözo gépeken lehetnek a kliens kérést küld (amire a szerver válaszol), így veszi igénybe a szolgáltatást

## Többrétegű architektúrák

### Elosztott információs rendszerek rétegelése

Az elosztott információs rendszerek gyakran három logikai rétegre („layer" vagy „tier") vannak tagolva.

### Háromrétegű architektúra

-   Megjelenítés (user interface): az alkalmazás felhasználói felületét alkotó komponensekbol áll
-   Üzleti logika (application): az alkalmazás működését írja le (konkrét adatok nélkül)
-   Perzisztencia (data layer): az adatok tartós tárolása

A három rétegbol néha több is egy gépen található meg.
Kétrétegű architektúra: kliens/egyszerű szerver Egyrétegű architektúra: nagygépre (mainframe) kötött terminal

## Decentralizált architektúrák

### Peer-to-peer architektúra

Az utóbbi években a peer-to-peer (P2P) architektúra egyre népszerűbbé válik. A „peer" szó arra utal, hogy a csúcsok között (többnyire) nincsenek kitüntetett szerepűek.

-   strukturált P2P: a csúcsok által kiadott gráfszerkezet rögzített
-   strukturálatlan P2P: a csúcsok szomszédai véletlenszerűek
-   hibrid P2P: néhány csúcsnak speciális szerepe van, ezek a többitol eltérő szervezésűek

### Overlay hálózat

overlay: A gráfban szomszédos csúcsok a fizikai hálózaton lehetnek távol egymástól, a rendszer elfedi, hogy a köztük levo kommunikáció több gépet érintve történik.

-   A legtöbb P2P rendszer overlay hálózatra épül

## Strukturált P2P rendszerek

A csúcsokat valamilyen struktúra szerint overlay hálózatba szervezzük (pl. logikai gyűrű), és a csúcsoktól az azonosítójuk alapján lehet szolgáltatásokat igénybe venni.

### Példa: d dimenziós particionált tér

Az adatoknak most d mezoje van, kulccsal nem rendelkeznek. Az így adódó tér fel van osztva annyi tartományra, ahány csúcsunk van; minden csúcs valamelyik tartomány adataiért felelos. Ha egy új csúcs érkezik, kettébontunk egy tartományt.

### Strukturálatlan P2P rendszer

A strukturálatlan P2P rendszerek igyekeznek véletlen gráfstruktúrát fenntartani.

-   Mindegyik csúcsnak csak részleges nézete van a gráfról (a teljes hálózatnak csak egy kis részét látja).
-   Minden P csúcs idoközönként kiválaszt egy szomszédos Q csúcsot
-   P és Q információt cserél, valamint átküldik egymásnak az általuk ismert csúcsokat

## Overlay hálózatok topológiájának kezelése

Különböztessünk meg két réteget: (1) az alsó rétegben a csúcsoknak csak részleges nézete van; (2) a felso rétegbe csak kevés csúcs kerülhet. Az alsó réteg véletlenszerű csúcsokat ad át a felso rétegnek; a felső réteg ezek közül csak keveset tart meg.

### Tórusz overlay topológia kialakítása

Ha megfeleloen választjuk meg, milyen csúcsokat tartson meg a felső réteg, akkor a kezdetben véletlenszerű overlay kapcsolatok hamarosan szabályos alakba rendezodnek. Itt egy távolságfüggvény szerinti megtartó szabály hat (az overlay a közelieket veszi át), és már az elso néhány lépés után jól látszik a kijövő tórusz-alakzat.

## Overlay topológia: példa: clusterezés

Most mindegyik i csúcshoz hozzárendelünk egy GID(i) ∈ N számot, és azt mondjuk, hogy i a GID(i) csoportba tartozik.

Szintén távolságfüggvényt használunk:

dist(i,j) =  1 ha GID(i) = GID(j)

0 ha GID(i) 6= GID(j)

Itt is igen gyorsan kialakul a kívánt szerkezet: csak az azonos csoportbeli csúcsok között lesz kapcsolat, kialakulnak a clusterek.

## Superpeer csúcsok

superpeer: olyan kisszámú csúcs, amelyeknek külön feladata van

## Hibrid arch.: kliens-szerver + P2P: edge szerver

edge szerver: az adatokat tároló szerver, a kliensekhez minél közelebb van elhelyezve, jellemzoen ott, ahol egy nagyobb hálózat az Internetre csatlakozik Content Delivery Network (CDN) rendszerekben jellemzo, a tartalomszolgáltatás hatékonyságát növelik és költségét csökkentik.

## Hibrid arch.: kliens-szerver + P2P: BitTorrent

Miután a csúcs kiderítette, melyik másik csúcsok tartalmaznak részeket a kívánt fájlból, azokat párhuzamosan tölti le, és egyúttal önmaga is kiajánlja megosztásra.

## Architektúrák és köztesréteg

Elofordulhat, hogy az elosztott rendszer/alkalmazás szerkezete nem felel meg a megváltozott igényeknek. Ilyenkor legtöbbször nem kell újraírni a teljes rendszert: elegendo lehet (dinamikusan) adaptálni a köztesréteg viselkedését.

### Interceptor

interceptor: Távoli objektum elérése során a vezérlés szokásos menetébe avatkozik bele, pl. átalakíthatja más formátumra a kérést. Jellemzoen az architektúra rétegei közé illeszthető.

## Adaptív middleware

-   Funkciók szétválasztása (separation of concerns): A szoftver különbözo jellegű funkciói váljanak minél jobban külön, így azokat könnyebb egymástól függetlenül módosítani.
-   Önvizsgálat (reflection): A program legyen képes feltárni a saját szerkezetét, és futás közben módosítani azt.
-   Komponensalapú szervezés: Az elosztott alkalmazás legyen moduláris, a komponensei legyenek könnyen cserélhetoek. A komponensek közötti függések legyenek egyértelműek, és csak annyi legyen belolük, amennyi feltétlenül szükséges

## Önszervezo elosztott rendszerek

### Adaptív rendszer képességei

Az egyes szoftverelemek adaptivitása kihat a rendszerre, ezért megvizsgáljuk, hogyan lehet adaptív rendszereket készíteni. Különféle elvárásaink lehetnek:

-   Önkonfiguráció
-   Önkezelo
-   Öngyógyító
-   Önoptimalizáló
-   Ön\*

## Adaptivitás visszacsatolással

### Visszacsatolásos modell

Az ön\* rendszerek sokszor az alábbi jellegű visszacsatolásos vezérléssel rendelkeznek: mérik, hogy a rendszer mennyire tér el a kívánt tulajdonságoktól, és szükség szerint változtatnak a beállításokon.

## Globule

-   Kollaboratív webes CDN, a tartalmakat költségmodell alapján helyezi el (minden szempontra: fontosság×költség).
-   A központi szerver (origin server) elemzi, ami történt, és az alapján állítja be a fontossági paramétereket, hogy mi történt volna, ha P oldalt az S edge szerver tárolta volna.
-   A számításokat különbözo stratégiákra végzi el, végül a legjobbat választja ki.

## Szálak: bevezetés

A legtöbb hardvereszköznek létezik szoftveres megfeleloje.

Processzor (CPU): Hardvereszköz, utasításokat képes sorban végrehajtani,
amelyek egy megadott utasításkészletbol származnak. 

Szál (thread): A processzor egyfajta szoftveres megfeleloje, minimális
kontextussal (környezettel). Ha a szálat megállítjuk, a kontextus
elmentheto és továbbfuttatáshoz visszatölthető.˝

Folyamat (process, task): Egy vagy több szálat összefogó nagyobb egység.
Egy folyamat szálai közös memóriaterületen (címtartományon)
dolgoznak, azonban különbözo folyamatok nem látják egymás
memóriaterületét.

## Kontextusváltás

-   kontextusváltás: A másik folyamatnak/szálnak történo˝
    vezérlésátadás, illetve a megfelelo kontextusok cseréje. Így egy
    processzor több folyamatot/szálat is végre tud hajtani.

-   Processzor kontextusa: Az utasítások végrehajtásában szerepet
    játszó kisszámú regiszter (elemi értéktároló) tartalma.

-   Szál kontextusa: Jellemzoen nem sokkal bővebb a
    processzorkontextusnál. A szálak közötti váltáshoz nem kell
    igénybe venni az operációs rendszer szolgáltatásait.

-   Folyamat kontextusa: Ahhoz, hogy a régi és az új folyamat
    memóriaterülete elkülönüljön, a memóriavezérlo (memory
    management unit, MMU) tartalmának jórészét át kell írni, amire
    csak a kernel szintnek van joga.
    A folyamatok létrehozása, törlése és a kontextusváltás köztük
    sokkal költségesebb a szálakénál.

## Szálak és operációs rendszerek

### Hol legyenek a szálak?

A szálakat kezelheti az operációs rendszer, vagy tole független
szálkönyvtárak. Mindkét megközelítésnek vannak elonyei és hátrányai.

### Szálak folyamaton belül: szálkönyvtárak

-   elony : Minden műveletet egyetlen folyamaton belül kezelünk, ez
    hatékony.
-   hátrány: Az operációs rendszer számára a szál minden művelete a
    gazdafolyamattól érkezik ⇒ ha a kernel blokkolja a szálat (pl.
    lemezművelet során), a folyamat is blokkolódik.
-   hátrány: Ha a kernel nem látja a szálakat közvetlenül, hogyan tud
    szignálokat közvetíteni nekik?

## Szálak és operációs rendszerek

### Szálak folyamaton kívül: kernelszintű szálak

A szálkönyvtárak helyezhetoek kernelszintre is. Ekkor minden szálművelet
rendszerhíváson keresztül érheto el.

-   elony : A szálak blokkolása nem okoz problémát: a kernel be tudja
    ütemezni a gazdafolyamat egy másik szálát.
-   elony : A szignálokat a kernel a megfelelo szálhoz tudja irányítani.
-   hátrány: Mivel minden művelet a kernelt érinti, ez a hatékonyság
    rovására megy.

## Solaris szálak

### Könnyűsúlyú folyamatok

könnyűsúlyú folyamat (lightweight process, LWP): Kernelszintű szálak,
amelyek felhasználói szintű szálkezeloket futtatnak.

## Szálak a kliensoldalon

### Többszálú webkliens

A hálózat késleltetésének elfedése:

-   A böngészo letöltött egy oldalt, ami több másik tartalomra hivatkozik.
-   Mindegyik tartalmat külön szálon tölti le, amíg a HTTP kéréseket
    kiszolgálják, ezek blokkolódnak.
-   Amikor egy-egy fájl megérkezik, a blokkolás megszűnik, és a böngészo˝
    megjeleníti a tartalmat.

### Több távoli eljáráshívás (RPC) egyszerre

-   Egy kliens több távoli szolgáltatást szeretne igénybe venni. Mindegyik
    kérést külön szál kezeli.
-   Megvárja, amíg mindegyik kérésre megérkezik a válasz.
-   Ha különbözo gépekre irányulnak a kérések, akár lineáris mértékű
    gyorsulás is elérheto így

## Szálak a szerveroldalon

### Cél: a hatékonyság növelése

-   Szálakat sokkal olcsóbb elindítani, mint folyamatokat (ido- és tárigény
    szempontjából egyaránt).
-   Mivel egy processzor csak egy szálat tud végrehajtani, a
    többprocesszoros rendszerek kapacitását csak többszálú szerverek
    képesek kihasználni.
-   A kliensekhez hasonlóan, a hálózat késleltetését lehet elfedni azzal, ha
    egyszerre több kérést dolgoz fel a szerver.

### Cél: a program szerkezetének javítása

-   A program jobban kezelheto lehet, ha sok egyszerű, blokkoló hívást
    alkalmaz, mint más szerkezet esetén. Ez némi teljesítményveszteséggel
    járhat.
-   A többszálú programok sokszor kisebbek és könnyebben érthetoek , mert
    jobban átlátható, merre halad a vezérlés.

## Virtualizáció

A virtualizáció szerepe egyre no több okból.

-   A hardver gyorsabban fejlodik a szoftvernél
-   Növeli a kód hordozhatóságát és költöztethetoségét
-   A hibás vagy megtámadott rendszereket könnyű így elkülöníteni

## A virtuális gépek szerkezete

### Virtualizálható komponensek

A rendszereknek sokfajta olyan rétege van, amely mentén virtualizálni
lehet a komponenseket. Mindig eldöntendo, milyen interfészeket kell
szolgáltatnia a virtuális gépnek (és milyeneket vehet igénybe).

## Process VM, VM monitor

-   Process VM: A virtuális gép (virtual machine, VM) közönséges
    programként fut, bájtkódot (elofordított programkódot) hajt végre.
    Pl. JVM, CLR, de vannak speciális célúak is, pl. ScummVM.
-   VM Monitor (VMM), hypervisor: Hardver teljeskörű virtualizációja,
    bármilyen program és operációs rendszer futtatására képes. Pl.
    VMware, VirtualBox.

## VM monitorok működése

Sok esetben a VMM egy operációs rendszeren belül fut.

-   A VMM a futtatott gépi kódú utasításokat átalakítja a gazdagép
    utasításaivá, és azokat hajtja végre.
-   A rendszerhívásokat és egyéb privilegizált utasításokat, amelyek
    végrehajtásához az operációs rendszer közreműködésére lenne
    szükség, megkülönböztetett módon kezeli.

## Kliens: átlátszóság

### A kliensekkel kapcsolatos fobb átlátszóságok

-   hozzáférési: az RPC kliensoldali csonkja
-   elhelyezési/áthelyezési: a kliensoldali szoftver tartja számon, hol
    helyezkedik el az eroforrás
-   többszörözési: a klienscsonk kezeli a többszörözött hívásokat
-   meghibásodási: sokszor csak a klienshez helyezheto – csak ott jelezhető˝
    a kommunikációs probléma

## Szerver: általános szerkezet

szerver: Olyan folyamat, amely egy (vagy akár több) porton várja a
kliensek kéréseit. Egy adott porton (ami egy 0 és 65535 közötti szám)
a szerver egyfajta szolgáltatást nyújt.
A 0-1023 portok közismert szolgáltatásokat nyújtanak, ezeket Unix
alapú rendszereken csak rendszergazdai jogosultsággal lehet foglalni.

### Szerverfajták

-   szuperszerver : Olyan szerver, amelyik több porton figyeli a bejövo˝
    kapcsolatokat, és amikor új kérés érkezik, új folyamatot/szálat indít
    annak kezelésére. Pl. Unix rendszerekben: inetd.
-   iteratív↔konkurens szerver : Az iteratív szerverek egyszerre csak egy
    kapcsolatot tudnak kezelni, a konkurensek párhuzamosan többet
    is.

## Szerver: sávon kívüli kommunikáció

### Sürgos üzenetek küldése

Meg lehet-e szakítani egy szerver működését kiszolgálás közben?

### Külön port

A szerver két portot használ, az egyik a sürgos üzeneteknek van fenntartva:

-   Ezt külön szál/folyamat kezeli
-   Amikor fontos üzenet érkezik, a normál üzenet fogadása szünetel
-   A szálnak/folyamatnak nagyobb prioritást kell kapnia, ehhez az
    oprendszer támogatása szükséges

### Sávon kívüli kommunikáció

Sávon kívüli kommunikáció használata, ha rendelkezésre áll:

-   Pl. a TCP protokoll az eredeti kérés kapcsolatán keresztül képes sürgos˝
    üzenetek továbbítására
-   Szignálok formájában kapható el a szerveren belül

## Szerver: állapot

### Állapot nélküli szerver

Nem tart fenn információkat a kliensrol a kapcsolat bontása után.

-   Nem tartja számon, melyik kliens milyen fájlból kért adatokat
-   Nem ígéri meg, hogy frissen tartja a kliens gyorsítótárát
-   Nem tartja számon a bejelentkezett klienseket: nincsen munkamenet
    (session)

#### Következmények

-   A kliensek és a szerverek teljesen függetlenek egymástól
-   Kevésbé valószínű, hogy inkonzisztencia lép fel azért, mert valamelyik
    oldal összeomlik
-   A hatékonyság rovására mehet, hogy a szerver nem tud semmit a
    kliensrol, pl. nem tudja előre betölteni azokat az adatokat, amelyekre a
    kliensnek szüksége lehet

### Állapotteljes szerverek

Állapotot tart számon a kliensekrol:

-   Megjegyzi, melyik fájlokat használta a kliens, és ezeket elore meg
    tudja nyitni legközelebb
-   Megjegyzi, milyen adatokat töltött le a kliens, és frissítéseket
    küldhet neki

#### Elonyök és hátrányok

Az állapotteljes szerverek nagyon hatékonyak tudnak lenni, ha a
kliensek lokálisan tárolhatnak adatokat.
Az állapotteljes rendszereket megfeleloen megbízhatóvá is lehet tenni
a hatékonyság jelentos rontása nélkül. 

## Szerver: háromrétegű clusterek

![](haromretegu.png)

## A kérések kezelése

### Szűk keresztmetszet

Ha minden kapcsolatot végig az elso réteg kezel, könnyen szűk
keresztmetszetté válhat.

A terhelés csökkentheto, ha a kapcsolatot átadjuk ( TCP handoff).

## Elosztott rendszerek: mobil IPv6

### Essence

A mobil IPv6-ot támogató kliensek az elosztott szolgáltatás bármelyik
peer-jéhez kapcsolódhatnak.

-   A C kliens kapcsolódik a szerver otthonának (home address, HA)
    IPv6 címéhez
-   A HA címen a szerver hazai ügynöke (home agent) fogadja a
    kérést, és a megfelelo˝ felügyeleti címre (care-of address, CA)
    továbbítja
-   Ezután C és CA már közvetlenül tudnak kommunikálni (HA
    érintése nélkül)

### kollaboratív CDN-ek

Az origin server tölti be HA szerepét, és átadja a beérkezo kapcsolatot
a megfelelo peer szervernek. A kliensek számára az origin és a peer
egy szervernek látszik.

## Kódmigráció: jellemzo feladatok

kódmigráció: olyan kommunikáció, amely során nem csak adatokat
küldünk át

Néhány jellemzo feladat, amelyhez kódmigrációra van szükség.

-   Client-Server: a szokásos kliens-szerver kommunikáció, nincsen
    kódmigráció
-   Remote Evaluation: a kliens feltölti a kódot, és a szerveren futtatja
-   Code on Demand: a kliens letölti a kódot a szerverrol, és helyben
    futtatja
-   Mobile Agent: a mobil ágens feltölti a kódját és az állapotát, és a
    szerveren folytatja a futását

## Kódmigráció: gyenge és eros mobilitás

### Objektumkomponensek

-   Kódszegmens: a programkódot tartalmazza
-   Adatszegmens: a futó program állapotát tartalmazza
-   Végrehajtási szegmens: a futtató szál környezetét tartalmazza

### Gyenge mobilitás

A kód- és adatszegmens mozgatása (a kód újraindul):

-   Viszonylag egyszerű megtenni, különösen, ha a kód hordozható
-   Irány szerint: feltöltés (push, ship), letöltés (pull, fetch)

### Eros mobilitás

A komponens a végrehajtási szegmenssel együtt költözik

-   Migráció: az objektum átköltözik az egyik géprol a másikra
-   Klónozás: a kód másolata kerül a másik gépre, és ugyanabból az
    állapotból indul el, mint az eredeti; az eredeti is fut tovább

## Kódmigráció: az eroforrások elérése

Az eredeti gépen található eroforrások költözés után a kód számára
távoliakká válnak.

### Eroforrás–gép kötés erőssége

-   Mozdíthatatlan: nem költöztetheto (pl. fizikai hardver)
-   Rögzített: költöztetheto, de csak drágán (pl. nagy adatbázis)
-   Csatolatlan: egyszerűen költöztetheto (pl. gyorsítótár)

### Komponens–eroforrás kötés jellege

Milyen jellegű eroforrásra van szüksége a komponensnek?

-   Azonosítókapcsolt: egy konkrét (pl. a cég adatbázisa)
-   Tartalomkapcsolt: adott tartalmú (pl. bizonyos elemeket
    tartalmazó cache)
-   Típuskapcsolt: adott jellegű (pl. színes nyomtató)

## Kódmigráció: az eroforrások elérése

### Kapcsolat az eroforrással

Hogyan tud a komponens kapcsolatban maradni az eroforrással?

-   Típuskapcsolt eroforrás esetén a legkönnyebb újrakapcsolódni
    egy lokális, megfelelo típusú erőforráshoz
-   Azonosítókapcsolt vagy tartalomkapcsolt esetben:
    -   rendszerszintű hivatkozást létesíthetünk az eredeti eroforrásra ,
        -   mozdíthatatlan eroforrások esetén ez az egyetlen lehetőség
        -   minden más esetben is szóba jöhet, de általában van jobb megoldás
    -   azonosítókapcsolt eroforrást érdemes áthelyezni
    -   tartalomkapcsolt eroforrást érdemes lemásolni

## Kódmigráció: heterogén rendszerben

### Nehézségek

-   A célgép nem biztos, hogy képes futtatni a migrált kódot
-   A processzor-, szál- és/vagy folyamatkörnyezet nagyban függ a
    lokális hardvertol, oprendszertől és futtatókörnyezettől

### Megoldás problémás esetekben

Virtuális gép használata: akár process VM, akár hypervisor.
Természetesen a virtuális gépnek elérhetonek kell lennie mindkét
környezetben.

# Kommunikacio

## Az ISO/OSI hálózatkezelési modell

![](osi.png)

### Hátrányok

-   Csak az üzenetküldésre koncentrál
-   Az (5) és (6) rétegek legtöbbször nem jelennek meg ilyen tisztán
-   Az elérési átlátszóság nem teljesül ebben a modellben

## Az alsó rétegek

### A rétegek feladatai

-   Fizikai réteg: a bitek átvitelének fizikai részleteit írja le
-   Adatkapcsolati réteg: az üzeneteket keretekre tagolja, célja a
    hibajavítás és a hálózat terhelésének korlátozása
-   Hálózati réteg: a hálózat távoli gépei között közvetít csomagokat
    útválasztás (routing) segítségével

## Szállítási réteg

### Absztrakciós alap

A legtöbb elosztott rendszer a szállítási réteg szolgáltatásaira épít.

### A legfobb protokollok

-   TCP: kapcsolatalapú, megbízható, sorrendhelyes átvitel
-   UDP: nem (teljesen) megbízható, általában kis üzenetek
    (datagram) átvitele

## Csoportcímzés

IP-alapú többcímű üzenetküldés (multicasting) sokszor elérheto, de
legfeljebb a lokális hálózaton belül használatos.

## Köztesréteg

### Szolgáltatásai

A köztesrétegbe (middleware) olyan szolgáltatásokat és protokollokat
szokás sorolni, amelyek sokfajta alkalmazáshoz lehetnek hasznosak.

-   Sokfajta kommunikációs protokoll
-   Sorosítás ((de)serialization, (un)marshalling), adatok
    reprezentációjának átalakítása (elküldésre vagy elmentésre)
-   Elnevezési protokollok az eroforrások megosztásának
    megkönnyítésére
-   Biztonsági protokollok a kommunikáció biztonságossá tételére
-   Skálázási mechanizmusok adatok replikációjára és
    gyorsítótárazására

### Alkalmazási réteg

Az alkalmazások készítoinek csak az alkalmazás-specifikus
protokollokat kell önmaguknak implementálniuk.

## A kommunikáció fajtái

-   idoleges (transient) vagy megtartó (persistent)
-   aszinkron vagy szinkron

### Idoleges vs megtartó

-   Megtartó kommunikáció: A kommunikációs rendszer hajlandó
    huzamosan tárolni az üzenetet.
-   Idoleges kommunikáció: A kommunikációs rendszer elveti az üzenetet,
    ha az nem kézbesítheto.

### A szinkronizáció lehetséges helyei

-   Az üzenet elindításakor
-   Az üzenet beérkezésekor
-   A kérés feldolgozása után

## Kliens–szerver modell

### Általános jellemzok˝

A kliens–szerver modell jellemzoen idoleges, szinkron kommunikációt
használ.

-   A kliensnek és a szervernek egyidoben kell aktívnak lennie.
-   A kliens blokkolódik, amíg a válasz meg nem érkezik.
-   A szerver csak a kliensek fogadásával foglalkozik, és a kérések
    kiszolgálásával.

### A szinkron kommunikáció hátrányai

-   A kliens nem tud tovább dolgozni, amíg a válasz meg nem érkezik
-   A hibákat rögtön kezelni kell, különben feltartjuk a klienst
-   Bizonyos feladatokhoz (pl. levelezés) nem jól illeszkedik

## Üzenetküldés

### Üzenetorientált köztesréteg (message-oriented middleware, MOM)

Megtartó, aszinkron kommunikációs architektúra.

-   Segítségével a folyamatok üzeneteket küldhetnek egymásnak
-   A küldo félnek nem kell válaszra várakoznia, foglalkozhat mással
-   A köztesréteg gyakran hibatűrést biztosít

## RPC: alapok

### Az RPC alapötlete

-   Az alprogramok használata természetes a fejlesztés során
-   Az alprogramok a jó esetben egymástól függetlenül működnek
    („fekete doboz”),
-   ... így akár egy távoli gépen is végrehajthatóak

## RPC: Hivas lepesei

![](rpc.png)

## RPC: paraméterátadás

### A paraméterek sorosítása

A második lépésben a klienscsonk elkészíti az üzenetet, ami az
egyszerű bemásolásnál összetettebb lehet.

-   A kliens- és a szervergépen eltérhet az adatábrázolás (eltéro˝
    bájtsorrend)
-   A sorosítás során bájtsorozat készül az értékbol
-   Rögzíteni kell a paraméterek kódolását:
    -   A primitív típusok reprezentációját (egész, tört, karakteres)
    -   Az összetett típusok reprezentációját (tömbök, egyéb
        adatszerkezetek)
-   A két csonknak fordítania kell a közös formátumról a gépeik
    formátumára

### RPC paraméterátadás szemantikája

-   Érték–eredmény szerinti paraméterátadási szemantika: pl. figyelembe
    kell venni, hogy ha (a kliensoldalon ugyanoda mutató) hivatkozásokat
    adunk át, azokról ez a hívott eljárásban nem látszik.
-   Minden feldolgozandó adat paraméterként kerül az eljáráshoz; nincsen
    globális hivatkozás.
    \###Átlátszóság
    Nem értheto el teljes mértékű elérési átlátszóság.

### Távoli hivatkozás

Távoli hivatkozás bevezetésével növelheto az elérési átlátszóságot:

-   A távoli adat egységesen érheto el
-   A távoli hivatkozásokat át lehet paraméterként adni

## Aszinkron RPC

### Az RPC „javítása”

A szerver nyugtázza az üzenet megérkezését. Választ nem vár

## Késleltetett szinkronizált RPC

### Késleltetett szinkronizált RPC

Ez két aszinkron RPC, egymással összehangolva.

## További lehetoség

A kliens elküldheti a kérését, majd idonként lekérdezheti a szervertől,
kész-e már a válasz.

## RPC: a kliens csatlakozása a szerverhez

### A kliens

-   A szolgáltatások katalógusba jegyzik be (globálisan és lokálisan
    is), melyik gépen érhetoek el. (1-2)
-   A kliens kikeresi a szolgáltatást a katalógusból. (3)
-   A kliens végpontot igényel a démontól a kommunikációhoz. (4)

## Üzenetorientált köztesréteg

### Működési elv

A köztesréteg várakozási sorokat (queue) tart fenn a rendszer gépein.
A kliensek az alábbi műveleteket használhatják a várakozási sorokra.

## Üzenetközvetíto˝

### Üzenetsorkezelo rendszer homogenitása

Az üzenetsorkezelo rendszerek feltételezik, hogy a rendszer minden
eleme közös protokollt használ, azaz az üzenetek szerkezete és
adatábrázolása megegyezo.˝

### Üzenetközvetíto˝

üzenetközvetíto (message broker) : Olyan központi komponens, amely
heterogén rendszerben gondoskodik a megfelelo konverziókról.

-   Átalakítja az üzeneteket a fogadó formátumára.
-   Szerepe szerint igen gyakran átjáró (application-level gateway,
    proxy) is, azaz a közvetítés mellet további (pl. biztonsági)
    funkciókat is nyújt
-   Az üzenetek tartalmát is megvizsgálhatják az útválasztáshoz
    (subject based vagy object based routing) ⇒ Enterprise
    Application Integration

## WebSphere MQ (IBM)

-   Az üzenetkezelok neve itt sorkezelő (queue manager); adott
    alkalmazásoknak címzett üzeneteket fogadnak
    -   Az üzenetkezelot össze lehet szerkeszteni a kliensprogrammal
    -   Az üzenetkezelo RPC-n keresztül is elérhető˝
-   Az útválasztótáblák (routing table) megadják, melyik kimeno˝
    csatornán kell továbbítani az üzenetet
-   A csatornákat üzenetcsatorna-ügynökök (message channel agent,
    MCA) kezelik
    -   Kiépítik a hálózati kapcsolatokat (pl. TCP/IP)
    -   Ki- és becsomagolják az üzeneteket, és fogadják/küldik a
        csomagokat a hálózatról

## Folyamatos média

### Az ido szerepe

Az eddig tárgyalt kommunikációfajtákban közös, hogy diszkrét
ábrázolásúak: az adategységek közötti idobeli kapcsolat nem
befolyásolja azok jelentését.

### Folyamatos ábrázolású média

A fentiekkel szemben itt a továbbított adatok idofüggőek .
Néhány jellemzo példa:

-   audio
-   videó
-   animációk
-   szenzorok adatai (homérséklet, nyomás stb.)

### Adatátviteli módok

Többfajta megkötést tehetünk a kommunikáció idobeliségével
kapcsolatban.

-   aszinkron: nem ad megkötést arra, hogy mikor kell átvinni az
    adatot
-   szinkron: az egyes adatcsomagoknak megadott idotartam alatt
    célba kell érniük
-   izokron vagy izoszinkrona
    : felso és alsó korlátot is ad a csomagok
    átvitelére; a remegés (jitter) így korlátozott mértékű

## Folyam

### Adatfolyam

adatfolyam: Izokron adatátvitelt támogató kommunikációs forma.

### Fontosabb jellemzok˝

-   Egyirányú
-   Legtöbbször egy forrástól (source) folyik egy vagy több nyelo˝
    (sink) felé
-   A forrás és/vagy a nyelo gyakran közvetlenül kapcsolódik
    hardverelemekhez (pl. kamera, képernyo)˝
-   egyszerű folyam: egyfajta adatot továbbít, pl. egy audiocsatornát
    vagy csak videót
-   összetett folyam: többfajta adatot továbbít, pl. sztereo audiót vagy
    hangot+videót

### Szolgáltatás minosége

A folyamokkal kapcsolatban sokfajta követelmény írható elo, ezeket
összefoglaló néven a szolgáltatás minoségének (Quality of Service,
QoS) nevezzük. Ilyen jellemzok a következők:

-   A folyam átvitelének „sebessége”: bit rate.
-   A folyam megindításának legnagyobb megengedett késleltetése.
-   A folyam adategységeinek megadott ido alatt el kell jutniuk a
    forrástól a nyeloig ( end-to-end delay), illetve számíthat az
    oda-vissza út is (round trip delay).
-   Az adategységek beérkezési idoközeinek egyenetlensége:
    remegés (jitter).

## Folyam: QoS biztosítása

### Differenciált szolgáltatási architektúra

Több hálózati eszköz érheto el, amelyekkel a QoS biztosítható.
Egy lehetoség, ha a hálózat routerei kategorizálják az áthaladó
forgalmat a beérkezo adatcsomagok tartalma szerint, és egyes
csomagfajtákat elsobbséggel továbbítanak ( differentiated services).

### A remegés csökkentése

A routerek pufferelhetik az adatokat a remegés csökkentésére.

## Összetett folyam szinkronizációja

### Szinkronizáció a nyelonél

Az összetett folyam alfolyamait szinkronizálni kell a nyelonél, különben
idoben elcsúszhatnának egymáshoz képest.

### Multiplexálás

Másik lehetoség: a forrás már eleve egyetlen folyamot készít
(multiplexálás). Ezek garantáltan szinkronban vannak egymással, a
nyelonél csak szét kellőket bontani ( demultiplexálás).

## Alkalmazásszintű multicasting

A hálózat minden csúcsának szeretnénk üzenetet tudjunk küldeni
(multicast). Ehhez hierarchikus overlay hálózatba szervezzük oket.

### Chord struktúrában tárolt fa készítése

-   A multicast hálózatunkhoz generálunk egy azonosítót, így
    egyszerre több multicast hálózatunk is lehet egy rendszerben.
-   Tegyük fel, hogy az azonosító egyértelműen kijelöl egy csúcsot a
    rendszerünkbena
    . Ez a csúcs lesz a fa gyökere.
-   Terv: a küldendo üzeneteket mindenki elküldi a gyökérhez, majd
    onnan a fán lefele terjednek.
-   Ha a P csúcs csatlakozni szeretne a multicast hálózathoz,
    csatlakozási kérést küld a gyökér felé. A P csúcstól a gyökérig
    egyértelmű az útvonalb
    ; ennek minden csúcsát a fa részévé
    teszünk (ha még nem volt az). Így P elérhetové válik a gyökértől.

## Alkalmazásszintű multicasting: költségek

-   Kapcsolatok terhelése: Mivel overlay hálózatot alkalmazunk,
    elofordulhat, hogy egy üzenetküldés többször is igénybe veszi
    ugyanazt a fizikai kapcsolatot.
    Példa: az A → D üzenetküldés kétszer halad át az Ra → Rb élen.
-   Stretch: Az overlayt követo és az alacsonyszintű üzenetküldés
    költségének hányadosa.
    Példa: B → C overlay költsége 71, hálózati 47 ⇒ stretch = 71/47.

## Járványalapú algoritmusok

### Alapötlet

-   Valamelyik szerveren frissítési műveletet (update) hajtottak végre,
    azt szeretnénk, hogy ez elterjedjen a rendszerben minden
    szerverhez.
-   Minden szerver elküldi a változást néhány szomszédjának
    (messze nem az összes csúcsnak) lusta módon (nem azonnal)
-   Tegyük fel, hogy nincs olvasás-írás konfliktus a rendszerben.

### Két alkategória

-   Anti-entrópia: Minden szerver rendszeresen kiválaszt egy másikat,
    és kicserélik egymás között a változásokat.
-   Pletykálás (gossiping): Az újonnan frissült (megfertozött ) szerver
    elküldi a frissítést néhány szomszédjának (megfertoziőket).

## Járvány: anti-entrópia

### A frissítések cseréje

-   P csúcs Q csúcsot választotta ki.
-   Küldés (push): P elküldi a nála levo frissítéseket Q-nak
-   Rendelés (pull): P bekéri a Q-nál levo frissítéseket
-   Küldés–rendelés (push–pull): P és Q kicserélik az adataikat, így
    ugyanaz lesz mindketto tartalma.

### Hatékonyság

A küldo–rendelő megközelítés esetébenő(log(N)) nagyságrendű
forduló megtétele után az összes csúcshoz eljut a frissítés.
Egy fordulónak az számít, ha mindegyik csúcs megtett egy lépést.

## Járvány: pletykálás

### Működési elv

Ha az S szerver új frissítést észlelt, akkor felveszi a kapcsolatot más
szerverekkel, és elküldi számukra a frissítést.
Ha olyan szerverhez kapcsolódik, ahol már jelen van a frissítés, akkor
1/k
valószínűséggel abbahagyja a frissítés terjesztését.

### Hatékonyság

Kelloen sok szerver esetén a tudatlanságban maradó szerverek
(akikhez nem jut el a frissítés) száma exponenciálisan csökken a k
valószínűség növekedésével, de ezzel az algoritmussal nem
garantálható, hogy minden szerverhez eljut a frissítés.

## Járvány: értékek törlése

### A törlési művelet nem terjesztheto˝

Ha egy adat törlésének műveletét is az eloz˝ oekhez hasonlóan
terjesztenénk a szerverek között, akkor a még terjedo frissítési
műveletek újra létrehoznák az adatot ott, ahová a törlés eljutott.

### Megoldás

A törlést speciális frissítésként: halotti bizonyítvány (death certificate)
küldésével terjesztjük.

### Halotti bizonyítvány törlése

A halotti bizonyítványt nem akarjuk örökké tárolni. Mikor törölhetoek?

-   Szemétgyűjtés-jellegű megközelítés: Egy rendszerszintű
    algoritmussal felismerjük, hogy mindenhová eljutott a bizonyítvány,
    és ekkor mindenhonnan eltávolítjuk. Ez a megoldás nem jól
    skálázódik.
-   elavuló bizonyítvány: Kibocsátás után adott idovel a bizonyítvány
    elavul, és ekkor törölheto; így viszont nem garantálható, hogy
    mindenhová elér.

# Elnevezési rendszerek