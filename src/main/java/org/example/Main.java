package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==프로그램 시작==");

        int lastArticleId = 0;

        List<Article> articles = new ArrayList<>();

        while (true) {
            System.out.print("명령어 ) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                break;
            } else if (cmd.length() == 0) {
                System.out.println("명령어 입력하세요");
                continue;
            }

            if (cmd.equals("article write")) {
                System.out.println("==게시글 작성==");
                int id = lastArticleId + 1;
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String body = sc.nextLine().trim();
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime newNow = LocalDateTime.now();


                Article article = new Article(id, title, body, now, newNow);
                articles.add(article);

                System.out.println(id + "번 글이 작성되었습니다.");
                lastArticleId++;
            } else if (cmd.equals("article list")) {
                System.out.println("==게시글 목록==");
                if (articles.size() == 0) {
                    System.out.println("아무것도 없음");
                } else {
                    System.out.println("   번호  /     제목     /   내용  ");
                    for (int i = articles.size() - 1; i >= 0; i--) {
                        Article article = articles.get(i);
                        System.out.printf("   %d    /    %s     /     %s   \n", article.getId(), article.getTitle(), article.getBody());
                    }
                }
            } else if (cmd.startsWith("article detail")) {
                System.out.println("==게시글 상세보기==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = null;

                for (Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getBody());
                System.out.println("작성 시각 : " + foundArticle.getNow());

            } else if (cmd.startsWith("article delete")) {
                System.out.println("==게시글 삭제==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = null;

                for (Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                articles.remove(foundArticle);
                System.out.println(id + "번 게시글이 삭제되었습니다");
            } else if (cmd.startsWith("article modify")) {
                System.out.println("==게시글 수정==");

                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = null;

                for (Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }

                if (foundArticle == null) {
                    System.out.println("해당 게시글은 없습니다");
                    continue;
                }
                System.out.println("기존 title : " + foundArticle.getTitle());
                System.out.println("기존 body : " + foundArticle.getBody());
                System.out.println("기존 now : " + foundArticle.getNow());
                System.out.print("새 제목 : ");
                String newTitle = sc.nextLine().trim();
                System.out.print("새 내용 : ");
                String newBody = sc.nextLine().trim();
                System.out.print("수정 시각 : ");
                foundArticle.setNow(LocalDateTime.now());
                System.out.println(LocalDateTime.now());


                foundArticle.setTitle(newTitle);
                foundArticle.setBody(newBody);
                System.out.println(id + "번 게시글이 수정되었습니다");
            } else {
                System.out.println("사용할 수 없는 명령어입니다");
            }
        }
        System.out.println("==프로그램 끝==");
        sc.close();
    }
}

class Article {
    private int id;
    private String title;
    private String body;
    private LocalDateTime now;


    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Article(int id, String title, String body, LocalDateTime now, LocalDateTime newNow) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.now = now;


    }
}