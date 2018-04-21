package ib.mantis;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.mantisbt.connect.Enumeration;
import org.mantisbt.connect.IMCSession;
import org.mantisbt.connect.MCException;
import org.mantisbt.connect.axis.MCSession;
import org.mantisbt.connect.model.IIssue;
import org.mantisbt.connect.model.IMCAttribute;
import org.mantisbt.connect.model.INote;
import org.mantisbt.connect.model.IProject;
import org.mantisbt.connect.ui.LocalFileIssueAttachment;




public class MantisBT {

	public static void main(String[] args){
		try
		{
			URL url = new URL("http://autotestscript.com/mantis/api/soap/mantisconnect.php");
			/*String user = "Pavan.K";
			String pwd = "Ideabytes@";
			String projectName="banking1";
			String priority="normal";*/
			String user = "naresh.n";
			String pwd = "naresh.n";
			String projectName="banking1";
			String priority="normal";
			//String attachmentFilePath="C:\\Users\\Rajesh\\Desktop\\Retrofit.png";
			String attachmentFilePath="/home/siddish/Pictures/Screenshot1.png";
		//	String severity="minor";
			String commentNotes="checking notes";
			IMCSession session = new MCSession(url, user, pwd);	
			IIssue issues=session.getIssue(33);
			System.out.println(issues.getId());

				
			//System.out.println(session.getVersion());
			
			
			IProject[] projects = session.getAccessibleProjects();
			IIssue issue = session.newIssue(33);
			long projectId=0;
			for(IProject project:projects)
			{
				if(project.getName().equals(projectName))
				{
					projectId=project.getId();
					//project.g
					System.out.println(project.getId()+":"+project.getName());
					break;
				}
				
			}
			if(projectId==0)
			{
				System.err.println(projectName+"could not found");
			}
			else
			{
				reportIssue(projectId,session,issue,priority);
				addAttachment(projectId,session,attachmentFilePath);
				addComment(session,session.getBiggestIssueId(projectId),commentNotes);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception is "+e);
		}
	}
public static void reportIssue(long projectId, IMCSession session, IIssue issue, String priority2)
{
	try {
		
		issue.setCategory("Change Request");
		issue.setStepsToReproduce("new steps 070716_2");
		issue.setSummary("Issue summary 070716_2");
		issue.setDescription("Issue description 070716_2");
		IMCAttribute[] priorities = null;
		
		//status=session.getEnum(Enumeration.);
		//status=session.getEnum(Enumeration.ACCESS_LEVELS);
		//result: viewer,reporter,updater,developer,manager,administrator
		//status=session.getEnum(Enumeration.PROJECT_STATUS);
		//result:development,release,stable,obsolete
		//status=session.getEnum(Enumeration.PROJECT_VIEW_STATES);
		//result:public,private
		//status=session.getEnum(Enumeration.STATUS);
		//result:new,feedback,acknowledged,confirmed,assigned,resolved,closed
		priorities=session.getEnum(Enumeration.PRIORITIES);
		//result:none,low,normal,high,urgent,immediate
		//status=session.getEnum(Enumeration.SEVERITIES);
		//result:feature,trivial,text,tweak,minor,major,crash,block
		//..........................
		//issue.setNotes(arg0);
		for(IMCAttribute priority:priorities)
		{
			if(priority.getName().equals(priority2))
					{
						issue.setPriority(priority);
						break;
					}
		}
		priorities=session.getEnum(Enumeration.STATUS);
		
		issue.setStatus(priorities[4]);
		issue.setAdditionalInformation("addtional information");
		session.addIssue(issue);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
}

public static void addAttachment(long projectId, IMCSession session, String filepath) throws MCException, IOException
{

	File file = new File(filepath);
	//File file = new File("C:\\Users\\Rajesh\\Desktop\\SVNError.png");
	//File file2 = new File("C:\\Users\\Rajesh\\Desktop\\truck.jpg");
	LocalFileIssueAttachment attachment = new LocalFileIssueAttachment(file);
	String filename="";
	//LocalFileIssueAttachment attachment2 = new LocalFileIssueAttachment(file2);
	if(filepath.contains("."))
	{
		filename=filepath.substring(filepath.lastIndexOf("\\")+1);
	}
	else
		System.out.println("Invalid attachmentFilePath");
	session.addIssueAttachment(session.getBiggestIssueId(projectId), filename, attachment.getContentType(), attachment.getData());
	//session.addIssueAttachment(session.getBiggestIssueId(projectId), "truck.jpg", attachment2.getContentType(), attachment2.getData());
}
public static void updateIssue(IMCSession session,long issueId) throws MCException
{
	
	IIssue issue =session.getIssue(issueId);
	System.out.println("update issue:"+issueId);
	//issue.setSummary("Issue summary 070716_updated");
//	session.updateIssue(issue);
}
public final static void addComment(IMCSession session,long issueId, String text) 
{
    try 
    {
        INote note = session.newNote(text);
         session.addNote(issueId, note);
    } 
    catch (MCException e) 
    {
    	System.out.println(e);
    }
 }

}

