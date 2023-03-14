package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");


        // 이런 이유 때문에 탬플릿 엔진이 나온것임
        // 비즈니스 로직은 어느정도 가능 하겠지만
        // html 태그를 뿌려주는 것이 너무 불편함 심지어 제대로 뿌려주지도 못함
        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write("   <meta charset=\"UTF-8\">");
        w.write("   <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write("   <thead>");
        w.write("   <th>id</th>");
        w.write("   <th>username</th>");
        w.write("   <th>age</th>");
        w.write("   </thead>");
        w.write("   <tbody>");

            /*
             w.write(" <tr>");
             w.write(" <td>1</td>");
             w.write(" <td>userA</td>");
             w.write(" <td>10</td>");
             w.write(" </tr>");
            */

        for (Member member : members) {
            w.write("   <tr>");
            w.write("       <td>" + member.getId() + "</td>");
            w.write("       <td>" + member.getName() + "</td>");
            w.write("       <td>" + member.getAge() + "</td>");
            w.write("   </tr>");
        }
        w.write("   </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }
}
