package ib.mantis;

import java.net.URL;

import org.apache.axis.session.Session;
import org.mantisbt.connect.IMCSession;
import org.mantisbt.connect.axis.AttachmentData;
import org.mantisbt.connect.axis.MCSession;
import org.mantisbt.connect.axis.ProjectAttachmentData;
import org.mantisbt.connect.model.IIssue;
import org.mantisbt.connect.model.Issue;

public class getIssue {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			URL url = new URL("http://autotestscript.com/mantis/api/soap/mantisconnect.php");
			String user = "Pavan.K";
			String pwd = "Ideabytes@";
			String projectName="banking1";
			IMCSession session=new MCSession(url, user, pwd);
			System.out.println(session.toString());
			long id=session.getBiggestIssueId(29);
			System.out.println(id);
			System.out.println(session.issueExists(2404));
			//System.out.println(session.getAccessibleProjects());
			//ProjectAttachmentData data=new ProjectAttachmentData();
			//System.out.println(session.getBiggestIssueId(33));
			System.out.println(session.getProjectAttachments(29));
	        //public IList<IProjectAttachment> GetProjectAttachments(long projectId)

			System.out.println(session.getIssueAttachment(2404));
			//session.add
			IIssue issues=session.getIssue(33);
		

			System.out.println(issues.getId());

			
			

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}


}
