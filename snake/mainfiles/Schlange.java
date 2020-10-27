
import sum.kern.*;
public class Schlange
{
    Bildschirm bildschirm;
    Tastatur eingabe;
    Buntstift buntstift;
    SchlangenElement erstesSchlangenElement;
    SchlangenElement hintern;
    sammelbaresElement frucht;
    Schlange nachfolger;
    double farbe;
    int n=0;
    int o=0;
    int s=0;
    int w=0;
    String richtung;
    String richtung2;
    public Schlange(Schlange Nachfolger, double Farbe)
    {
        farbe=Farbe;
        bildschirm=Main.bildschirm;
        buntstift=Main.zn1;
        eingabe=Main.eingabe;
        nachfolger=Nachfolger;
        erstesSchlangenElement=new SchlangenElement(40, null, 26);;
        hintern=new SchlangenElement(40, erstesSchlangenElement, 27);
        verändern(0.5);
        richtung="N";
        richtung2="N";
        for(int i=0; i<5; i++)
        {
            hinzufügen();
        }
    }

        public void verändern(double farbe)
    {
        if(frucht!=null)
        {
            if(farbe<0.5){buntstift.setzeFarbe(Farbe.GELB);}else{buntstift.setzeFarbe(Farbe.BLAU);}
            malen(frucht.x, true, frucht.y);
        }
        frucht=new sammelbaresElement((int)(Math.random()*75), (double)Math.random()*1, (int)(Math.random()*75));
        if(frucht.farbe<0.5){buntstift.setzeFarbe(Farbe.ORANGE);}else{buntstift.setzeFarbe(Farbe.MAGENTA);}
        malen(frucht.x, true, frucht.y);
    }
    
    public void entfernen()
    {
        if(erstesSchlangenElement!=null)
        {
            buntstift.setzeFarbe(Farbe.GRUEN);
            malen(hintern.x, false, hintern.y);
            hintern=hintern.nachfolger;
        }
    }

    public void hinzufügen()
    {
        if(erstesSchlangenElement!=null)
        {
            if(farbe<0.5){buntstift.setzeFarbe(Farbe.GRUEN);}else{buntstift.setzeFarbe(Farbe.ROT);}
            malen(erstesSchlangenElement.x, true, erstesSchlangenElement.y);
            if(richtung=="N")erstesSchlangenElement.nachfolger=new SchlangenElement(erstesSchlangenElement.x, null, erstesSchlangenElement.y-1);
            if(richtung=="O")erstesSchlangenElement.nachfolger=new SchlangenElement(erstesSchlangenElement.x+1, null, erstesSchlangenElement.y);
            if(richtung=="S")erstesSchlangenElement.nachfolger=new SchlangenElement(erstesSchlangenElement.x, null, erstesSchlangenElement.y+1);
            if(richtung=="W")erstesSchlangenElement.nachfolger=new SchlangenElement(erstesSchlangenElement.x-1, null, erstesSchlangenElement.y);
            erstesSchlangenElement=erstesSchlangenElement.nachfolger;
            if(erstesSchlangenElement.x==-1)erstesSchlangenElement.x=74;
            if(erstesSchlangenElement.y==-1)erstesSchlangenElement.y=74;
            if(erstesSchlangenElement.x==75)erstesSchlangenElement.x=0;
            if(erstesSchlangenElement.y==75)erstesSchlangenElement.y=0;
            buntstift.setzeFarbe(Farbe.ROT);
            malen(erstesSchlangenElement.x, false, erstesSchlangenElement.y);
        }
    }

    public void radieren()
    {
        erstesSchlangenElement=null;
        hintern=null;
        frucht=null;
    }

    public void malen(int X, boolean r, int Y)
    {
        buntstift.bewegeBis(X*10+4, Y*10+4);
        buntstift.zeichneRechteck(1, 1);
        buntstift.bewegeBis(X*10+3, Y*10+3);
        buntstift.zeichneRechteck(3, 3);
        buntstift.bewegeBis(X*10+2, Y*10+2);
        buntstift.zeichneRechteck(5, 5);
        buntstift.bewegeBis(X*10+1, Y*10+1);
        buntstift.zeichneRechteck(7, 7);
        if(r)buntstift.setzeFarbe(Farbe.SCHWARZ);
        buntstift.bewegeBis(X*10+0, Y*10+0);
        buntstift.zeichneRechteck(9, 9);
    }
    
    public void eingabe()
    {
        if(eingabe.wurdeGedrueckt() && (int)eingabe.zeichen()==(int)n)
            {if(richtung!="S"){richtung2="N";}
            eingabe.weiter();Main.ack=true;}
        if(eingabe.wurdeGedrueckt() && (int)eingabe.zeichen()==(int)o)
            {if(richtung!="W"){richtung2="O";}
            eingabe.weiter();Main.ack=true;}
        if(eingabe.wurdeGedrueckt() && (int)eingabe.zeichen()==(int)s)
            {if(richtung!="N")
                {richtung2="S";}eingabe.weiter();
                Main.ack=true;}
        if(eingabe.wurdeGedrueckt() && (int)eingabe.zeichen()==(int)w)
            {if(richtung!="O")
                {richtung2="W";}eingabe.weiter();
                Main.ack=true;}
    }
}
