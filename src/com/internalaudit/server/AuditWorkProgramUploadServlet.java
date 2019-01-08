
package com.internalaudit.server;

//public class UploadServlet {
//
//}

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.james.mime4j.field.FieldName;

import com.google.gwt.user.client.Window;

public class AuditWorkProgramUploadServlet extends HttpServlet {

	 @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp)
           throws ServletException, IOException {
       super.doGet(req, resp);
   }
   
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp)
           throws ServletException, IOException {
       
       // process only multipart requests
       if (ServletFileUpload.isMultipartContent(req)) {

           // Create a factory for disk-based file items
           FileItemFactory factory = new DiskFileItemFactory();

           // Create a new file upload handler
           ServletFileUpload upload = new ServletFileUpload(factory);

           // Parse the request
           
           HttpSession session = req.getSession(true);
          // int auditStepId = (Integer) session.getAttribute("auditStep");
           try {
               List<FileItem> items = upload.parseRequest(req);
               ArrayList<String> fileNames = new ArrayList<String>();
               for (FileItem item : items) {
                   // process only file upload - discard other form item types
                   if (item.isFormField()) continue;
                   
                   String fileName = item.getName();
                   // get only the file name not whole path
                   if (fileName != null) {
                       fileName = FilenameUtils. getName(fileName);
                       fileNames.add(fileName); 
                       
                   }
                   
                   ////substring//
                   String fieldName = item.getFieldName();
                   int index = fieldName.indexOf(":");
                   String id = fieldName.substring(0, index);
                   String mainFolder = fieldName.substring(index+1);
                   
                   ////
                   String realPath = getServletContext().getRealPath("/");
                   File folder = new File(realPath+"/"+mainFolder);
                   folder.mkdirs();
           
                   
                   File auditSteps = new File(folder+"/"+id);
                   auditSteps.mkdirs();
                   File uploadedFile = new File(auditSteps, fileName);
                   
                   if (uploadedFile.exists()){
                  	 uploadedFile.delete();
                   }
                  	 
               
                      if (uploadedFile.createNewFile()) {
                       item.write(uploadedFile);
                       resp.setStatus(HttpServletResponse.SC_CREATED);
                       resp.getWriter().print("The file was created successfully.");
                  
                       resp.flushBuffer();
                   } else 
                     
                  	 throw new IOException("The file already exists in repository.");
               }
             //  session.setAttribute("auditStepUploadedFiles", fileNames);
           } catch (Exception e) {
               resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                       "An error occurred while creating the file : " + e.getMessage());
           }

       } else {
           resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE,
                           "Request contents type is not supported by the servlet.");
       }
   }
}