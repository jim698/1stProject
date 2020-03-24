package com.tj.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.project.dao.ProductsDao;
import com.tj.project.dto.ProductsDto;

public class PinsertService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String path = request.getRealPath("productsImg");
		int maxSize = 1024 * 1024 * 5; // 최대 업로드 사이즈를 5메가로 하겠다!
		String[] image = { "", "", "" }; //
		MultipartRequest mRequest = null;

		try {
			// 파일 서버에 올리고 파일 이름 받기(multipartRequest 객체 이용)
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());

			Enumeration<String> paramNames = mRequest.getFileNames(); //
			int idx = 0;
			while (paramNames.hasMoreElements()) {
				String param = paramNames.nextElement();
				image[idx] = mRequest.getFilesystemName(param);
				idx++;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// 서버에 올려진 파일을 소스폴더에 복사

		for (String imgfile : image) {
			if (imgfile != null) {
				InputStream is = null;
				OutputStream os = null;
				File serverFile = new File(path + "/" + imgfile);

				try {

					if (serverFile.exists()) {
						is = new FileInputStream(serverFile);
						os = new FileOutputStream(
								"E:\\mega_it\\parrotProject\\parrotshop\\WebContent\\productsImg\\" + imgfile);
						byte[] bs = new byte[(int) serverFile.length()];
						while (true) {
							int readbyteCnt = is.read(bs);
							if (readbyteCnt == -1) {
								break;
							}

							os.write(bs, 0, readbyteCnt);
						}

					}

				} catch (Exception e) {
					System.out.print(e.getMessage());
				} finally {
					try {
						if (os != null)
							os.close();
						if (is != null)
							is.close();
					} catch (Exception e) {
						System.out.print(e.getMessage());
					}
				}
			}

		}

		String pkinds = mRequest.getParameter("pkinds");
		String pname = mRequest.getParameter("pname");
		String ptitle = mRequest.getParameter("ptitle");
		String pphotologo = image[1] != null ? image[1] : "youngman.jpg";
		String pphoto = image[0] != null ? image[0] : null;
		String pphoto2 = image[2] != null ? image[2] : null;
		String pcontent = mRequest.getParameter("pcontent");
		int pcnt = Integer.parseInt(mRequest.getParameter("pcnt"));
		int pprice = Integer.parseInt(mRequest.getParameter("pprice"));
		int pdiscount = Integer.parseInt(mRequest.getParameter("pdiscount")); 

		ProductsDao pDao = ProductsDao.getInstance();
		ProductsDto product = new ProductsDto(0, pkinds, pname, pphotologo, pphoto, pphoto2, pcontent, pcnt, pprice,
				pdiscount, null);

		int result = pDao.insertProducts(product);

		if (result == ProductsDao.SUCCESS) {
			request.setAttribute("pinsertResult", "상품 등록 성공");
		} else {
			request.setAttribute("pinsertResult", "상품 등록 실패");
		}

	}

}
