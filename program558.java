import java.util.*;
import java.time.*;
import java.io.*;

//Done
class StudyLog
{
    public LocalDate Date;
    public String Subject;
    public double Duration;
    public String Description;

    public StudyLog(LocalDate A,String B, Double C, String D)
    {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Description = D;
    }

    @Override
    public String toString()
    {
        return Date+"   |   "+Subject+"    |   "+Duration+"    |   "+Description;
    }

    //getter method
    public LocalDate getDate()
    {
        return Date;
    }

    public String getSubject()
    {
        return Subject;
    }

    public double getDuration()
    {
        return Duration;
    }

    public String getDescription()
    {
        return Description;
    }

}

class StudyTracker
{
    //Data structure to hold data about study
    private ArrayList <StudyLog> Database = new ArrayList <StudyLog> ();

    public void InsertLog()
    {
        Scanner Scannerobj = new Scanner(System.in);

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("----------------please enter the valid details of your study------------------------");
        System.out.println("------------------------------------------------------------------------------------");

        LocalDate DateObj = LocalDate.now();

        System.out.println("Please provide name of subjects like C/C++/Java/OS/DS");
        String sub = Scannerobj.nextLine();

        System.out.println("Enter the time period of your study in hours");
        double dur = Scannerobj.nextDouble();
        Scannerobj.nextLine();

        System.out.println("Please provide the description about the study for future reference");
        String desc = Scannerobj.nextLine();

        StudyLog StudyObj = new StudyLog(DateObj, sub, dur, desc);

        Database.add(StudyObj);

        System.out.println("Study log gets stores successfully");
        System.out.println("------------------------------------------------------------------------------------");

    }

    public void DisplayLog()
    {
        System.out.println("------------------------------------------------------------------------------------");
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("---------------------------------------------------------------------------------");
            return;

        }

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("----------------Log Report from Marvellous Study Tracker----------------------------");
        System.out.println("------------------------------------------------------------------------------------");

        for(StudyLog sobj : Database)
        {
            System.out.println(sobj);
        }
        System.out.println("------------------------------------------------------------------------------------");

    }

    public void ExportCSV()
    {
        if(Database.isEmpty())
        {
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("Nothing to export as database is empty");
            System.out.println("---------------------------------------------------------------------------------");
            return;

        }

        String FileName = "MarvellousStudy.csv";

        //create new csv file
        try(FileWriter fwobj = new FileWriter(FileName))
        {
            fwobj.write("Date,Subject,Duration,Description\n");

            for(StudyLog sobj : Database)
            {
                fwobj.write(sobj.getDate()+","+
                            sobj.getSubject().replace(',',' ')+","+
                            sobj.getDuration()+","+
                            sobj.getDescription().replace(',',' ')+"\n"
                );
            }

            System.out.println("Log Created successfully");
        }
        catch(Exception obj)
        {
            System.out.println("exception occured while creating the csv");
            System.out.println("report this issue to Utkarsh");
        }
    }

    public void SummaryByDate()
    {
       System.out.println("------------------------------------------------------------------------------------");
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("---------------------------------------------------------------------------------");
            return;

        }

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("----------------Summary By Date from Marvellous Study Tracker-----------------------");
        System.out.println("------------------------------------------------------------------------------------");

        TreeMap<LocalDate,Double> tobj = new TreeMap<LocalDate,Double>();

        LocalDate lobj = null;
        double d, old;

        for(StudyLog sobj : Database)
        {
            lobj = sobj.getDate();
            d = sobj.getDuration();

            if(tobj.containsKey(lobj))
            {
                old = tobj.get(lobj);
                tobj.put(lobj,d+old);
            }
            else
            {
                tobj.put(lobj,d);
            }
        }
        //Display deatils as per date

        for(LocalDate ldobj : tobj.keySet())
        {
            System.out.println("Date : "+ldobj+"Total Study : "+tobj.get(ldobj));
        }
        System.out.println("------------------------------------------------------------------------------------");
 
    }

    public void SummaryBySubject()
    {
       System.out.println("------------------------------------------------------------------------------------");
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("---------------------------------------------------------------------------------");
            return;

        }

        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("----------------Summary By Subject from Marvellous Study Tracker--------------------");
        System.out.println("------------------------------------------------------------------------------------");

        TreeMap<String,Double> tobj = new TreeMap<String,Double>();

        double d, old;
        String s;

        for(StudyLog sobj : Database)
        {
            d = sobj.getDuration();
            s = sobj.getSubject();

            if(tobj.containsKey(s))
            {
                old = tobj.get(s);
                tobj.put(s,d+old);
            }
            else
            {
                tobj.put(s,d);
            }
        }
        //Display deatils as per subject

        for(String strobj : tobj.keySet())
        {
            System.out.println("Date : "+strobj+"Total Study : "+tobj.get(strobj));
        }
        System.out.println("------------------------------------------------------------------------------------");
 
    }
}

class program558   //StudyTrackerStarter
{
    public static void main(String[] args) 
    {
        StudyTracker stobj = new StudyTracker();

        Scanner scobj = new Scanner(System.in);

        int iChoice = 0;
        
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("-------------Welcome to Marvellous Study Tracker Application------------------------");
        System.out.println("------------------------------------------------------------------------------------");

        do
        {
            System.out.println("Please select the appropriate option : ");
            System.out.println("1 : Insert new study log into database");
            System.out.println("2 : View all study logs");
            System.out.println("3 : Summary  of study log by date");
            System.out.println("4 : summary of study log by subject");
            System.out.println("5 : Export study log to csv file");
            System.out.println("6 : Exit the application");

            iChoice = scobj.nextInt();

            switch (iChoice) {
                case 1: //Insert new study log into database
                    stobj.InsertLog();
                    break;

                case 2: //View all study logs
                    stobj.DisplayLog();
                    break;
                    
                case 3:
                    stobj.SummaryByDate();
                    break;
                case 4:
                    stobj.SummaryBySubject();
                    break;
                case 5:
                    stobj.ExportCSV();
                    break;
                case 6:
                    System.out.println("------------------------------------------------------------------------------------");
                    System.out.println("Thank you for using marvellous study log application");
                    System.out.println("------------------------------------------------------------------------------------");
                    break;
            
                default:
                    System.out.println("Please enter valid choice");
                    
            }

        }while(iChoice != 6);
    }    
}
