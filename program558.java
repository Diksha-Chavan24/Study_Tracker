import java.util.*;
import java.time.LocalDate;
import java.io.*;

//DONE
class StudyLog
{
    public LocalDate Date;
    public String Subject;
    public double Duration;
    public String Description;

    public StudyLog(LocalDate A, String B, double C, String D)
    {
        this.Date = A;
        this.Subject = B;
        this.Duration = C;
        this.Description = D;
    }

    @Override
    public String toString()
    {
        return Date+" | "+Subject+" | "+Duration+" | "+Description;
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
    // Data structure to hold the data aobout study
    private ArrayList <StudyLog> Database = new ArrayList <StudyLog> ();

    public void InsertLog()
    {
        Scanner ScannerObj = new Scanner(System.in);

        System.out.println("-------------------------------------------------------------------");
        System.out.println("------------Please enter the valid details of your study-----------");
        System.out.println("-------------------------------------------------------------------");
        
        LocalDate DateObj = LocalDate.now();
        
        System.out.println("Please provide the name of subject like C/C++/Java/OS/DS");
        String sub = ScannerObj.nextLine(); 
        
        System.out.println("Enter the time period of your study in hours");
        double dur = ScannerObj.nextDouble();
        ScannerObj.nextLine();
        
        System.out.println("Please the description about the study for future referance");
        String desc = ScannerObj.nextLine();
        
        StudyLog StudyObj = new StudyLog(DateObj,sub,dur,desc);
        
        Database.add(StudyObj);
        
        System.out.println("StudyLog gets stored successfully");
        System.out.println("-------------------------------------------------------------------");
    }

    public void DisplayLog()
    {
        System.out.println("-------------------------------------------------------------------");
        
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("-------------------------------------------------------------------");
            return;
        }
        
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Log report from marvellous StudyTraker");
        System.out.println("-------------------------------------------------------------------");
        
        for(StudyLog sobj : Database)
        {
            System.out.println(sobj);
        }
        System.out.println("-------------------------------------------------------------------");
        
    }

    public void ExportCSV()
    {
        if(Database.isEmpty())
        {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Nothing to export as database is empty");
            System.out.println("-------------------------------------------------------------------");
            return;
        }

        String Filename = "MarvellousStudy.csv";

        // create new csv file
        try(FileWriter fwobj = new FileWriter(Filename))
        {
            // write csv header
            fwobj.write("Date,Subject,Duration,Description\n");

            // travel database
            for(StudyLog sobj : Database)
            {
                // write each record in csv
                fwobj.write(sobj.getDate()+","+
                            sobj.getSubject().replace(",", " ")+","+
                            sobj.getDuration()+","+
                            sobj.getDescription().replace(",", " ")+"\n"
                );
            }

            System.out.println("Log created successfully");
        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured while creating the csv");
            System.out.println("Report this issue to Marvellous Infosystems");
        }
    }

    public void SummaryByDate()
    {
        System.out.println("-------------------------------------------------------------------");
        
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("-------------------------------------------------------------------");
            return;
        }
        
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Summary by date from marvellous StudyTraker");
        System.out.println("-------------------------------------------------------------------");
        
        TreeMap <LocalDate, Double> tobj = new TreeMap<LocalDate, Double>();

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

        // Display the details as per date
        for(LocalDate ldobj : tobj.keySet())
        {
            System.out.println("Date : "+ldobj+" Total study "+tobj.get(ldobj));
        }
        System.out.println("-------------------------------------------------------------------");
    }

    public void SummaryBySubject()
    {
        System.out.println("-------------------------------------------------------------------");
        
        if(Database.isEmpty())
        {
            System.out.println("Nothing to display as database is empty");
            System.out.println("-------------------------------------------------------------------");
            return;
        }
        
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Summary by subject from marvellous StudyTraker");
        System.out.println("-------------------------------------------------------------------");
        
        TreeMap <String, Double> tobj = new TreeMap<String, Double>();

        double d, old;
        String s;

        for(StudyLog sobj : Database)
        {
            s = sobj.getSubject();
            d = sobj.getDuration();

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

        // Display the details as per subject
        for(String str : tobj.keySet())
        {
            System.out.println("Subject : "+str+" Total study "+tobj.get(str));
        }
        
        
    }
}

class program558    // StudyTrackerStarter
{
    public static void main(String A[])
    {
        StudyTracker stobj = new StudyTracker();

        int iChoice = 0;
        
        Scanner ScannerObj = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------------");
        System.out.println("----------Welcome to Marvellous studyTracker application------------");
        System.out.println("-------------------------------------------------------------------");
        
        do
        {
            System.out.println("Plase select the appropriate option");
            System.out.println("1 : Insert new study Log into database");
            System.out.println("2 : View All Study Logs");
            System.out.println("3 : Summary of study Log by Date");
            System.out.println("4 : Summary of study Log by Subject");
            System.out.println("5 : Export study Log to CSV file");
            System.out.println("6 : Exit");
            
            iChoice = ScannerObj.nextInt();
            ScannerObj.nextLine();
            
            switch (iChoice) 
            {
                case 1: //Insert new study Log into database
                    stobj.InsertLog();
                    break;
                
                case 2 :    //View All Study Logs
                    stobj.DisplayLog();
                    break;
                
                case 3: //Summary of study Log by Date
                    stobj.SummaryByDate();
                break;
                
                case 4 :    //Summary of study Log by Subject 
                    stobj.SummaryBySubject();
                break;
                
                case 5: //Export study Log to CSV file
                    stobj.ExportCSV();
                    break;
                
                case 6 :    // Exit 
                    System.out.println("-------------------------------------------------------------------");
                    System.out.println("Thank you for using Marvellous StudyTracker application");
                    System.out.println("-------------------------------------------------------------------");
                    break;

                default:
                    System.out.println("Please enter valid option");
                    break;
            }

        }while(iChoice != 6);

    }
}
