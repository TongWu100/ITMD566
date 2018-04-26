package org.great.ctrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.great.entity.Buy;
import org.great.entity.Car;
import org.great.entity.CarPart;
import org.great.entity.Files;
import org.great.entity.Power;
import org.great.entity.User;
import org.great.util.FileUtil;
import org.great.util.Util;
import org.great.util.UuidHelper;

public class BusinessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1,uri.lastIndexOf("."));
		
		String path = req.getContextPath();
		String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";
		//System.out.println("uri:"+uri+",path:"+path+",basePath:"+basePath+",action:"+action);
		
		if (action.equals("webLogin")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			Map<String, String> map = BusinessDaoFactory.getInstance().getBusinessDao().login(username, password);
			if (map.get("isLogin").equals("true") ) {
				HttpSession session = req.getSession();
				session.setAttribute("userName", username);
				session.setAttribute("password", password);
				session.setAttribute("role", map.get("role"));
				session.setAttribute("roles", map.get("role").toString().equals("0")?"管理员":"普通用户");
				session.setAttribute("email", map.get("email"));
				session.setAttribute("city", map.get("city"));
				session.setAttribute("streetaddress", map.get("streetaddress"));
				session.setAttribute("account", map.get("account"));
				out.write("Success");
			} else {
				out.write("Fail");
			}
			out.close();
			
		}else if(action.equals("webRegist")){
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String state = req.getParameter("state");
			String city = req.getParameter("city");
			String streetaddress = req.getParameter("streetaddress");
			
			String reg1 = "[a-zA-Z_0-9]+@[a-zA-Z_0-9]+\\.[a-zA-Z_0-9]{2,3}";
		    String reg2 = "[a-zA-Z]{2}";
		    Pattern p1 = Pattern.compile(reg1);
		    Matcher m1 = p1.matcher(email);
		    Pattern p2 = Pattern.compile(reg2);
		    Matcher m2 = p2.matcher(state);
		    if(!(username.length()>=4&&username.length()<=50)){
		 	   out.print("File1");
		    }else if(!(password.length()>=5)){
		     	out.print("File2");
		     }else if(!(email.length()>=7&&m1.matches())){
		     	out.println("File3");
		     }
//		     else if(!m2.matches()){
//			    out.println("File4");
//			 }
		     else if(state.length()>=3&&state.length()<=50){
				    out.println("File4");
				 }
		     else if(city.length()>=3&&city.length()<=50){
			    out.println("File5");
			 }else if(!(streetaddress.length()>=4&&streetaddress.length()<=50)){
		     	out.println("File6");
		     }else{
		    	 	User user =new User();
					user.setUsername(username);
					user.setPassword(password);
					user.setEmail(email);
					user.setState(state);
					user.setCity(city);
					user.setStreetaddress(streetaddress);
					user.setRole("2");
					user.setAccount("0");
					
					if(BusinessDaoFactory.getInstance().getBusinessDao().getUser(email,username)==null){
					
					if (BusinessDaoFactory.getInstance().getBusinessDao().addUser(user)) {
						BusinessDaoFactory.getInstance().getBusinessDao().addRights(username);
						out.write("Success");
					}else {
						out.write("File");
					}
					}else{
						out.write("File");
					} 
		     }	
		    out.close();
		} else if (action.equals("img_upload")) {
//			upload image of car or carpart 
//			汽车或汽车配件图片上传
//			To ensure server security, the uploaded files should be placed in a directory that is not directly accessible to the outside such as WEB-INF directory
//			1、为保证服务器安全，上传文件应该放在外界无法直接访问的目录下，比如放于WEB-INF目录下。
//          To prevent file coverage, create a unique file name for the uploaded file.
//			2、为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名。
//          To prevent too many files from appearing under a directory, use the hash algorithm to store.
//			3、为防止一个目录下面出现太多文件，要使用hash算法打散存储。
//          Limit the maximum number of uploaded files.
//			4、要限制上传文件的最大值。
//          To limit the type of upload file, determine whether the suffix name is legal when you receive the file name
//			5、要限制上传文件的类型，在收到上传文件名时，判断后缀名是否合法
//          Get the saved directory of the uploaded files, store the uploaded files in the WEB-INF directory, and do not allow the outside direct access to ensure the security of the uploaded files.
			// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
//			String savePath = this.getServletContext().getRealPath(
//					"/WEB-INF/upload");
			//String savePath = this.getClass().getClassLoader().getResource("/WEB-INF/upload").getPath();
			String savePath = req.getSession().getServletContext()
					.getRealPath("/WEB-INF/upload");
//			Temporary files that are generated when uploaded save the directory.
			// 上传时生成的临时文件保存目录
//			String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
			//String tempPath = this.getClass().getClassLoader().getResource("/WEB-INF/temp").getPath();
			String tempPath = req.getSession().getServletContext().getRealPath("/WEB-INF/temp");
			File tmpFile = new File(tempPath);
			if (!tmpFile.exists()) {
				// Create temporary directory
				//创建临时目录
				tmpFile.mkdir();
			}
             //message alert
			// 消息提示
			String message = "";
			try {
				// 使用Apache文件上传组件处理文件上传步骤：
				// 1、创建一个DiskFileItemFactory工厂
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
				factory.setSizeThreshold(1024 * 100);// 设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
				// 设置上传时生成的临时文件的保存目录
				factory.setRepository(tmpFile);
				// 2、创建一个文件上传解析器
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 监听文件上传进度
				upload.setProgressListener(new ProgressListener() {
					public void update(long pBytesRead, long pContentLength,
							int arg2) {
						System.out.println("文件大小为：" + pContentLength + ",当前已处理："
								+ pBytesRead);
						/**
						 * 文件大小为：14608,当前已处理：4096 文件大小为：14608,当前已处理：7367
						 * 文件大小为：14608,当前已处理：11419 文件大小为：14608,当前已处理：14608
						 */
					}
				});
				// 解决上传文件名的中文乱码
				upload.setHeaderEncoding("UTF-8");
				// 3、判断提交上来的数据是否是上传表单的数据
				if (!ServletFileUpload.isMultipartContent(req)) {
					// 按照传统方式获取数据
					return;
				}

				// 设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
				upload.setFileSizeMax(1024 * 1024);
				// 设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
				upload.setSizeMax(1024 * 1024 * 10);
				// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
				List<FileItem> list = upload.parseRequest(req);
				for (FileItem item : list) {
					// 如果fileitem中封装的是普通输入项的数据
					if (item.isFormField()) {
						String name = item.getFieldName();
						// 解决普通输入项的数据的中文乱码问题
						String value = item.getString("UTF-8");
						// value = new String(value.getBytes("iso8859-1"),"UTF-8");
						System.out.println(name + "=" + value);
					} else {// 如果fileitem中封装的是上传文件
							// 得到上传的文件名称，
						String filename = item.getName();
						System.out.println(filename);
						if (filename == null || filename.trim().equals("")) {
							continue;
						}
						// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
						// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						filename = filename
								.substring(filename.lastIndexOf("\\") + 1);
						// 得到上传文件的扩展名
						String fileExtName = filename.substring(filename
								.lastIndexOf(".") + 1);
						// 如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
						System.out.println("上传的文件的扩展名是：" + fileExtName);
						// 获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						// 得到文件保存的名称
						String saveFilename = makeFileName(filename);
						// 得到文件的保存目录
						String realSavePath = makePath(saveFilename, savePath);
						// 创建一个文件输出流
						FileOutputStream out2 = new FileOutputStream(realSavePath
								+ "\\" + saveFilename);
						// 创建一个缓冲区
						byte buffer[] = new byte[1024];
						// 判断输入流中的数据是否已经读完的标识
						int len = 0;
						// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
						while ((len = in.read(buffer)) > 0) {
							// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
							// + filename)当中
							out2.write(buffer, 0, len);
						}
						// 关闭输入流
						in.close();
						// 关闭输出流
						out2.close();
						// 删除处理文件上传时生成的临时文件
						// item.delete();
						message = "文件上传成功！";
					}
				}
			} catch (FileUploadBase.FileSizeLimitExceededException e) {
				e.printStackTrace();
				req.setAttribute("message", "单个文件超出最大值！！！");
				req.getRequestDispatcher("/message.jsp").forward(req,
						resp);
				return;
			} catch (FileUploadBase.SizeLimitExceededException e) {
				e.printStackTrace();
				req.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
				req.getRequestDispatcher("/message.jsp").forward(req,
						resp);
				return;
			} catch (Exception e) {
				message = "文件上传失败！";
				e.printStackTrace();
			}
			req.setAttribute("message", message);
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}else if (action.equals("img_upload2")) {
			// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
			String savePath = req.getSession().getServletContext().getRealPath(
					"/upload");
			File file = new File(savePath);
			// 判断上传文件的保存目录是否存在
			if (!file.exists() && !file.isDirectory()) {
				System.out.println(savePath + "目录不存在，需要创建");
				// 创建目录
				file.mkdir();
			}
			// 消息提示
			String message = "";
			try {
				// 使用Apache文件上传组件处理文件上传步骤：
				// 1、创建一个DiskFileItemFactory工厂
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 2、创建一个文件上传解析器
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 解决上传文件名的中文乱码
				upload.setHeaderEncoding("UTF-8");
				// 3、判断提交上来的数据是否是上传表单的数据
				if (!ServletFileUpload.isMultipartContent(req)) {
					// 按照传统方式获取数据
					message = "文件上传失败！";
					out.println(message);
					return;
				}
				// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
				List<FileItem> list = upload.parseRequest(req);
				for (FileItem item : list) {
					// 如果fileitem中封装的是普通输入项的数据
					if (item.isFormField()) {
						String name = item.getFieldName();
						// 解决普通输入项的数据的中文乱码问题
						String value = item.getString("UTF-8");
						// value = new String(value.getBytes("iso8859-1"),"UTF-8");
						System.out.println(name + "=" + value);
					} else {// 如果fileitem中封装的是上传文件
							// 得到上传的文件名称，
						String filename = item.getName();
						System.out.println(filename);
						if (filename == null || filename.trim().equals("")) {
							continue;
						}
						// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
						// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						filename = filename
								.substring(filename.lastIndexOf("\\") + 1);
						String filecode = new UuidHelper().generate().toString();
						String fileNames = filecode+ filename.substring(filename.indexOf("."));
						String publishPath = savePath + "\\" + fileNames;
						// 获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						// 创建一个文件输出流
						FileOutputStream out2 = new FileOutputStream(publishPath);
						// 创建一个缓冲区
						byte buffer[] = new byte[1024];
						// 判断输入流中的数据是否已经读完的标识
						int len = 0;
						// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
						while ((len = in.read(buffer)) > 0) {
							// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
							// + filename)当中
							out2.write(buffer, 0, len);
						}
						// 关闭输入流
						in.close();
						// 关闭输出流
						out2.close();
						// 删除处理文件上传时生成的临时文件
						item.delete();
						message = "文件上传成功！";
						
						Files f =new Files();
						f.setFilecode(filecode);
						f.setFilepath(publishPath);
						
						if (BusinessDaoFactory.getInstance().getBusinessDao().addFiles(f)) {
							out.write("Success");
						}else {
							out.write("File");
						}
						
					}
				}
			} catch (Exception e) {
				message = "文件上传失败！";
				e.printStackTrace();

			}
			//req.setAttribute("message", message);
			//req.getRequestDispatcher("/message.jsp").forward(req, resp);
			out.println(message);
			out.close();
		}else if (action.equals("img_upload3")) {

			String filecode = new UuidHelper().generate().toString();
			String publishPath = "";
			String name1 = "name";
			String name2 = "buyprice";
			String name = "";
			String buyprice = "";
			// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
			String savePath = req.getSession().getServletContext().getRealPath(
					"/");
			File file = new File(savePath);
			// 判断上传文件的保存目录是否存在
			if (!file.exists() && !file.isDirectory()) {
				System.out.println(savePath + "目录不存在，需要创建");
				// 创建目录
				file.mkdir();
			}
			// 消息提示
			String message = "";
			try {
				// 使用Apache文件上传组件处理文件上传步骤：
				// 1、创建一个DiskFileItemFactory工厂
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 2、创建一个文件上传解析器
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 解决上传文件名的中文乱码
				upload.setHeaderEncoding("UTF-8");
				// 3、判断提交上来的数据是否是上传表单的数据
				if (!ServletFileUpload.isMultipartContent(req)) {
					// 按照传统方式获取数据
					message = "文件上传失败！";
					out.println(message);
					return;
				}
				// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
				List<FileItem> list = upload.parseRequest(req);
				for (FileItem item : list) {
					// 如果fileitem中封装的是普通输入项的数据
					if (item.isFormField()) {
						String name0 = item.getFieldName();
						// 解决普通输入项的数据的中文乱码问题
						String value = item.getString("UTF-8");
						// value = new String(value.getBytes("iso8859-1"),"UTF-8");
						System.out.println(name0 + "=" + value);
						if(name0.equals(name1)){
							name = value;
						}else if(name0.equals(name2)){
							buyprice = value;
						}
						
					} else {// 如果fileitem中封装的是上传文件
							// 得到上传的文件名称，
						String filename = item.getName();
						System.out.println(filename);
						if (filename == null || filename.trim().equals("")) {
							continue;
						}
						// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
						// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						filename = filename
								.substring(filename.lastIndexOf("\\") + 1);
						String fileNames = filecode+ filename.substring(filename.indexOf("."));
						//publishPath = savePath + "\\" + fileNames;
						String uploadPath = savePath + "upload";
						
						File uploadDir = new File(uploadPath);
						if (!uploadDir.exists()) {
							uploadDir.mkdirs();
						}
						
						publishPath = uploadPath + "/" + fileNames;
						
						// 获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						// 创建一个文件输出流
						FileOutputStream out2 = new FileOutputStream(publishPath);
						// 创建一个缓冲区
						byte buffer[] = new byte[1024];
						// 判断输入流中的数据是否已经读完的标识
						int len = 0;
						// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
						while ((len = in.read(buffer)) > 0) {
							// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
							// + filename)当中
							out2.write(buffer, 0, len);
						}
						// 关闭输入流
						in.close();
						// 关闭输出流
						out2.close();
						// 删除处理文件上传时生成的临时文件
						item.delete();
						
//						Files f =new Files();
//						f.setFilecode(filecode);
//						f.setFilepath(publishPath);
//						
//						if (BusinessDaoFactory.getInstance().getBusinessDao().addFiles(f)) {
//							out.write("Success");
//						}else {
//							out.write("File");
//						}
						
						String dirpath = basePath + "upload/" ;
						String imgpath = dirpath + fileNames;
						String webroot =  Util.getInstance().getWebRoot();
						
						file = new File(webroot);
//						// 判断上传文件的保存目录是否存在
						if (!file.exists() && !file.isDirectory()) {
							file.mkdir();
						}
						
						File imageFile=new File(publishPath);
						File dstDir = new File(webroot);
						FileUtil.copy(imageFile, dstDir);//复制用户上传图片到本地磁盘
						
						double buyprices = Double.parseDouble(buyprice);
						
						CarPart carpart =new CarPart();
						carpart.setName(name);
						carpart.setBuyprice(buyprices);
						carpart.setFilecode(filecode);
						carpart.setFilepath(publishPath);
						carpart.setImgpath(imgpath);
						
//						if (BusinessDaoFactory.getInstance().getBusinessDao().addCarPart(carpart)) {
//							out.write("Success");
//						}else {
//							out.write("Fail");
//						}
						
						if (BusinessDaoFactory.getInstance().getBusinessDao().addCarPart(carpart)) {
							message = "文件上传成功！";
							req.setAttribute("message", message);
						}else {
							message = "文件上传失败！";
							req.setAttribute("message", message);
						}
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = "文件上传失败！";
				req.setAttribute("message", message);

			}
			//req.setAttribute("message", message);
			//req.getRequestDispatcher("/message.jsp").forward(req, resp);
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}else if (action.equals("img_upload4")) {

			String filecode = new UuidHelper().generate().toString();
			String publishPath = "";
			String name1 = "name";
			String name2 = "type";
			String name3 = "buyPrice";
			String name4 = "rentPrice";
			String name = "";
			String type = "";
			String buyPrice = "";
			String rentPrice = "";
			// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
			String savePath = req.getSession().getServletContext().getRealPath(
					"/");
			File file = new File(savePath);
			// 判断上传文件的保存目录是否存在
			if (!file.exists() && !file.isDirectory()) {
				System.out.println(savePath + "目录不存在，需要创建");
				// 创建目录
				file.mkdir();
			}
			// 消息提示
			String message = "";
			try {
				// 使用Apache文件上传组件处理文件上传步骤：
				// 1、创建一个DiskFileItemFactory工厂
				DiskFileItemFactory factory = new DiskFileItemFactory();
				// 2、创建一个文件上传解析器
				ServletFileUpload upload = new ServletFileUpload(factory);
				// 解决上传文件名的中文乱码
				upload.setHeaderEncoding("UTF-8");
				// 3、判断提交上来的数据是否是上传表单的数据
				if (!ServletFileUpload.isMultipartContent(req)) {
					// 按照传统方式获取数据
					message = "文件上传失败！";
					out.println(message);
					return;
				}
				// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
				List<FileItem> list = upload.parseRequest(req);
				for (FileItem item : list) {
					// 如果fileitem中封装的是普通输入项的数据
					if (item.isFormField()) {
						String name0 = item.getFieldName();
						// 解决普通输入项的数据的中文乱码问题
						String value = item.getString("UTF-8");
						// value = new String(value.getBytes("iso8859-1"),"UTF-8");
						System.out.println(name0 + "=" + value);
						if(name0.equals(name1)){
							name = value;
						}else if(name0.equals(name2)){
							type = value;
						}
						else if(name0.equals(name3)){
							buyPrice = value;
						}
						else if(name0.equals(name4)){
							rentPrice = value;
						}
						
						
					} else {// 如果fileitem中封装的是上传文件
							// 得到上传的文件名称，
						String filename = item.getName();
						System.out.println(filename);
						if (filename == null || filename.trim().equals("")) {
							continue;
						}
						// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
						// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						filename = filename
								.substring(filename.lastIndexOf("\\") + 1);
						String fileNames = filecode+ filename.substring(filename.indexOf("."));
						//publishPath = savePath + "\\" + fileNames;
						String uploadPath = savePath + "upload";
						
						File uploadDir = new File(uploadPath);
						if (!uploadDir.exists()) {
							uploadDir.mkdirs();
						}
						
						publishPath = uploadPath + "/" + fileNames;
						
						// 获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						// 创建一个文件输出流
						FileOutputStream out2 = new FileOutputStream(publishPath);
						// 创建一个缓冲区
						byte buffer[] = new byte[1024];
						// 判断输入流中的数据是否已经读完的标识
						int len = 0;
						// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
						while ((len = in.read(buffer)) > 0) {
							// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
							// + filename)当中
							out2.write(buffer, 0, len);
						}
						// 关闭输入流
						in.close();
						// 关闭输出流
						out2.close();
						// 删除处理文件上传时生成的临时文件
						item.delete();
						
//						Files f =new Files();
//						f.setFilecode(filecode);
//						f.setFilepath(publishPath);
//						
//						if (BusinessDaoFactory.getInstance().getBusinessDao().addFiles(f)) {
//							out.write("Success");
//						}else {
//							out.write("File");
//						}
						
						String dirpath = basePath + "upload/" ;
						String imgpath = dirpath + fileNames;
						String webroot =  Util.getInstance().getWebRoot();
						
						file = new File(webroot);
//						// 判断上传文件的保存目录是否存在
						if (!file.exists() && !file.isDirectory()) {
							file.mkdir();
						}
						
						File imageFile=new File(publishPath);
						File dstDir = new File(webroot);
						FileUtil.copy(imageFile, dstDir);//复制用户上传图片到本地磁盘
						
						double buyprices = Double.parseDouble(buyPrice);
						double rentprices = Double.parseDouble(rentPrice);
						
						Car car =new Car();
						car.setName(name);
						car.setType(type);
						car.setBuyPrice(buyprices);
						car.setRentPrice(rentprices);
						car.setFilecode(filecode);
						car.setFilepath(publishPath);
						car.setImgpath(imgpath);
						
						if (BusinessDaoFactory.getInstance().getBusinessDao().addCar(car)) {
							message = "文件上传成功！";
							req.setAttribute("message", message);
						}else {
							message = "文件上传失败！";
							req.setAttribute("message", message);
						}
												
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = "文件上传失败！";
				req.setAttribute("message", message);

			}
			//req.setAttribute("message", message);
			//req.getRequestDispatcher("/message.jsp").forward(req, resp);
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}else if (action.equals("exit")) {
			HttpSession session = req.getSession();
			session.removeAttribute("userName");
			session.removeAttribute("role");
			session.removeAttribute("email");
			resp.sendRedirect("login.jsp");
		}else if (action.equals("listshow")) {
			String rightid = req.getParameter("rightid");
			Power power = BusinessDaoFactory.getInstance().getBusinessDao().select_power(rightid);
			JSONArray jsonArr = JSONArray.fromObject(power);
			String jsonStr = jsonArr.toString();
			out.println(jsonStr);
			out.close();
		}else if(action.equals("user_list")){
			List<User> list = BusinessDaoFactory.getInstance().getBusinessDao().getUserList();
			JSONArray jsonArr = JSONArray.fromObject(list);
			String jsonStr = jsonArr.toString();
			out.println(jsonStr);
			out.close();
		}else if(action.equals("user_list2")){
			HttpSession session = req.getSession();
			String email  = (String) session.getAttribute("email");
			List<User> list = BusinessDaoFactory.getInstance().getBusinessDao().getUserLists(email);
			JSONArray jsonArr = JSONArray.fromObject(list);
			String jsonStr = jsonArr.toString();
			out.println(jsonStr);
			out.close();
		}
		else if(action.equals("user_del")){
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			BusinessDaoFactory.getInstance().getBusinessDao().delUser(email);
			if(BusinessDaoFactory.getInstance().getBusinessDao().delRights(username)){
				out.write("Success");
			}else{
				out.write("Fail");
			}
			
			out.close();
		}else if(action.equals("user_add")){
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String repassword = req.getParameter("repassword");
			String email = req.getParameter("email");
			String rights_id = req.getParameter("rights_id");
			String state = req.getParameter("state");
			String city = req.getParameter("city");
			String streetAddress = req.getParameter("streetAddress");
			
			User user =new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setState(state);
			user.setCity(city);
			user.setStreetaddress(streetAddress);
			user.setRole("2");
			user.setAccount("0");
						
			if(BusinessDaoFactory.getInstance().getBusinessDao().getUser(email,username)==null){
			
			if (BusinessDaoFactory.getInstance().getBusinessDao().addUser(user)) {
				BusinessDaoFactory.getInstance().getBusinessDao().addRights(username);
				out.write("Success");
			}else {
				out.write("File");
			}
			}else{
				out.write("File");
			}
		}else if(action.equals("account_list")){
			List<User> list = BusinessDaoFactory.getInstance().getBusinessDao().getUserList2();
			JSONArray jsonArr = JSONArray.fromObject(list);
			String jsonStr = jsonArr.toString();
			out.println(jsonStr);
			out.close();
		}else if(action.equals("account_get")){
			String email = req.getParameter("email");
			String account = BusinessDaoFactory.getInstance().getBusinessDao().getUserAccount(email);
			out.println(account);
			out.close();
		}else if(action.equals("account_update")){
			String email = req.getParameter("email");
			User user = BusinessDaoFactory.getInstance().getBusinessDao().getUser(email);
			HttpSession session = req.getSession();
			req.setAttribute("username", user.getUsername());
			req.setAttribute("email", user.getEmail());
			req.setAttribute("password", user.getPassword());
			req.setAttribute("account", user.getAccount());
			//req.setAttribute("role", user.getRole().equals("0")?"管理员":"普通用户");
			req.setAttribute("role", user.getRole());
			req.setAttribute("state", user.getState());
			req.setAttribute("city", user.getCity());
			req.setAttribute("streetaddress", user.getStreetaddress());
			//resp.sendRedirect("account_update2.jsp");
			//req.getRequestDispatcher("account_update2.jsp").forward(req, resp);
			if(user == null){
				out.write("File");
			}else{
				JSONArray jsonArr = JSONArray.fromObject(user);
				String jsonStr = jsonArr.toString();
				out.println(jsonStr);
			}
			
			out.close();
		}else if(action.equals("user_account_update")){
			String email = req.getParameter("email");
			String account = req.getParameter("account");
			String state = req.getParameter("state");
			String city = req.getParameter("city");
			String streetAddress = req.getParameter("streetAddress");
			
			User user =new User();
			user.setEmail(email);
			user.setAccount(account);
			user.setState(state);
			user.setCity(city);
			user.setStreetaddress(streetAddress);
			
			if (BusinessDaoFactory.getInstance().getBusinessDao().updateUser2(user)) {
				out.write("Success");
			}else {
				out.write("File");
			}
		}else if(action.equals("car_list")){
			List<Car> list = BusinessDaoFactory.getInstance().getBusinessDao().getCarList();
			JSONArray jsonArr = JSONArray.fromObject(list);
			String jsonStr = jsonArr.toString();
			out.println(jsonStr);
			out.close();
		}else if(action.equals("car_del")){
			String id = req.getParameter("id");
			boolean flag = BusinessDaoFactory.getInstance().getBusinessDao().delCar(id);
			if(flag){
				out.write("Success");
			}else{
				out.write("File");
			}

			out.close();
		}else if(action.equals("car_buy")){
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			String resu = req.getParameter("resu");
			String name = req.getParameter("name");
			String type = req.getParameter("type");
			
			Date day=new Date();  
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String time = df.format(day);  
			
			int buyn = 0;
			int temp = 0;
			String buynum = "";
			String buynums = BusinessDaoFactory.getInstance().getBusinessDao().getBuyNum(email);
			if("0".equals(buynums)){
				buynum = "1";
			}else{
				temp = Integer.parseInt(buynums);
				buyn = temp + 1;
				buynum = String.valueOf(buyn);
			}
			
			User user =new User();
			user.setEmail(email);
			user.setAccount(resu);
			
			if (BusinessDaoFactory.getInstance().getBusinessDao().updateUser3(user)) {
				Buy buy = new Buy();
				buy.setName(name);
				buy.setBuynum("1");
				buy.setBuytime(time);
				buy.setBuyuser(username);
				buy.setBuyuseremail(email);
				buy.setBuytype(type);
				
				if (BusinessDaoFactory.getInstance().getBusinessDao().addBuyRecord(buy)) {
					out.write("Success");
				}else {
					out.write("File");
				}
//				String counts = BusinessDaoFactory.getInstance().getBusinessDao().getBuyCount(email,name);
//				if("0".equals(counts)){
//					if (BusinessDaoFactory.getInstance().getBusinessDao().addBuyRecord(buy)) {
//						out.write("Success");
//					}else {
//						out.write("File");
//					}
//				}else{
//					if (BusinessDaoFactory.getInstance().getBusinessDao().addBuyRecord(buy)) {
//						out.write("Success");
//					}else {
//						out.write("File");
//					}
//				}
				
				
			}else {
				out.write("File");
			}
		}else if(action.equals("car_add")){
			String name = req.getParameter("name");
			String type = req.getParameter("type");
			String buyPrice = req.getParameter("buyPrice");
			String rentPrice = req.getParameter("rentPrice");
			double buyPrices = Double.parseDouble(buyPrice);
			double rentPrices = Double.parseDouble(rentPrice);
			
			Car car =new Car();
			car.setName(name);
			car.setType(type);
			car.setBuyPrice(buyPrices);
			car.setRentPrice(rentPrices);
			
			if (BusinessDaoFactory.getInstance().getBusinessDao().addCar(car)) {
				out.write("Success");
			}else {
				out.write("File");
			}
		}else if(action.equals("carpart_list")){
			List<CarPart> list = BusinessDaoFactory.getInstance().getBusinessDao().getCarPartList();
			JSONArray jsonArr = JSONArray.fromObject(list);
			String jsonStr = jsonArr.toString();
			out.println(jsonStr);
			out.close();
		}
		else if(action.equals("carpart_del")){
			String id = req.getParameter("id");
			boolean flag = BusinessDaoFactory.getInstance().getBusinessDao().delCarPart(id);
			if(flag){
				out.write("Success");
			}else{
				out.write("File");
			}

			out.close();
		}else if(action.equals("carpart_add")){
			String name = req.getParameter("name");
			String buyprice = req.getParameter("buyprice");
			double buyprices = Double.parseDouble(buyprice);
			
			CarPart carpart =new CarPart();
			carpart.setName(name);
			carpart.setBuyprice(buyprices);
//			carpart.setFilecode(filecode);
			
			if (BusinessDaoFactory.getInstance().getBusinessDao().addCarPart(carpart)) {
				out.write("Success");
			}else {
				out.write("File");
			}
		}else if(action.equals("buy_list")){
			List<Buy> list = BusinessDaoFactory.getInstance().getBusinessDao().getBuyList();
			JSONArray jsonArr = JSONArray.fromObject(list);
			String jsonStr = jsonArr.toString();
			out.println(jsonStr);
			out.close();
		}
		else if(action.equals("test")){
			String message = "测试";
			req.setAttribute("message", message);
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
	}

	@Override
	public void destroy() {

	}
	
	/**
	 * @Method: makeFileName
	 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	 * @Anthor:zrm
	 * @param filename
	 *            文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */
	private String makeFileName(String filename) { // 2.jpg
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString() + "_" + filename;
	}

	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 * 
	 * @Method: makePath
	 * @Description:
	 * @Anthor:zrm
	 *
	 * @param filename
	 *            文件名，要根据文件名生成存储目录
	 * @param savePath
	 *            文件存储路径
	 * @return 新的存储目录
	 */
	private String makePath(String filename, String savePath) {
		// 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		// 构造新的保存目录
		String dir = savePath + "\\" + dir1 + "\\" + dir2; // upload\2\3
															// upload\3\5
		// File既可以代表文件也可以代表目录
		File file = new File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir;
	}

	public String getDate() {
		Date dt = new Date();
		SimpleDateFormat matter = new SimpleDateFormat("yyyy/MM/dd");
		return matter.format(dt);
	}

	public String decode(HttpServletRequest req) {
		String str = req.getParameter("pakage");
		try {
			str = URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public <Anything> void outPrintObject(Anything obj, PrintWriter out) {
		if (obj != null) {
			out.print(JSONObject.fromObject(obj).toString());
		} else {
			out.print("failed");
		}
		out.close();
	}

	public <Anything> void outPrintArray(Anything obj, PrintWriter out) {
		if (obj != null) {
			out.print(JSONArray.fromObject(obj).toString());
		} else {
			out.print("failed");
		}
		out.close();
	}

	public void outPrintString(boolean flag, PrintWriter out) {
		if (flag) {
			out.print("done");
		} else {
			out.print("failed");
		}
		out.close();
	}

	public String[] getArr(HttpServletRequest req) {
		String s[] = decode(req).split("\"");
		String arr[] = s[1].split(",");
		return arr;
	}
}
