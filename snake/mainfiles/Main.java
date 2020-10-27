
import sum.kern.*;
public class Main
{
    //Definition Schlange
    Schlange Element1;
    Schlange SystemElement1;
    Schlange SystemElement2;
    SchlangenElement glied1;
    // Variablen-Deklaration
    public static Bildschirm bildschirm;
    public static Tastatur eingabe;
    public static Buntstift zn1;
    public static boolean ack;

    
    public Main()
    {
        bildschirm=new Bildschirm(800, 600);
        eingabe=new Tastatur();
        zn1=new Buntstift();
        Element1=null;
        bildschirm.setzeFarbe(Farbe.GELB);
        while (true) // Erzeugung einer Endlosschleife
        {
            while(eingabe.wurdeGedrueckt())
            {
                SystemElement1=Element1;
                ack=false;
                while(SystemElement1!=null)
                {
                    SystemElement1.eingabe();
                    SystemElement1=SystemElement1.nachfolger;
                }
                if(!ack && Element1!=null && (int)Element1.n==(int)0)
                {
                    Element1.n=(int)eingabe.zeichen();
                    ack=true;
                }
                if(!ack && Element1!=null && (int)Element1.o==(int)0)
                {
                    Element1.o=(int)eingabe.zeichen();
                    ack=true;
                }
                if(!ack && Element1!=null && (int)Element1.s==(int)0)
                {
                    Element1.s=(int)eingabe.zeichen();
                    ack=true;
                }
                if(!ack && Element1!=null && (int)Element1.w==(int)0)
                {
                    Element1.w=(int)eingabe.zeichen();
                    ack=true;
                }
                if(!ack)
                {
                    Element1=new Schlange(Element1, (double)Math.random()*1);
                }
            }
            try{Thread.sleep(100);}catch(Exception ex){}
            SystemElement1=Element1;
            while(SystemElement1!=null)
            {
                SystemElement1.richtung=SystemElement1.richtung2;
                SystemElement1.entfernen();
                SystemElement1.hinzufügen();
                SystemElement2=Element1;
                while(SystemElement2!=null)
                {
                    glied1=SystemElement2.hintern;
                    while(glied1!=null && glied1.nachfolger!=null)
                    {
                        if(SystemElement1.erstesSchlangenElement!=null && SystemElement1.erstesSchlangenElement.x==glied1.x && SystemElement1.erstesSchlangenElement.y==glied1.y){SystemElement1.radieren();}
                        glied1=glied1.nachfolger;
                    }
                    if(SystemElement1.erstesSchlangenElement!=null && SystemElement2.frucht!=null && SystemElement1.erstesSchlangenElement.x==SystemElement2.frucht.x && SystemElement1.erstesSchlangenElement.y==SystemElement2.frucht.y){SystemElement1.hinzufügen();SystemElement2.verändern(SystemElement1.farbe);}
                    SystemElement2=SystemElement2.nachfolger;
                }
                SystemElement1=SystemElement1.nachfolger;
            }
        }
    }
}
