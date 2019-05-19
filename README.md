# cs623assignmentJDBC

###Queries:
#● Get a submitted paper’s details by the author’s Primary Key. The query should return the following data (columns): Paper.Id, Paper.Title, Paper.Abstract, Author.EmailAddress, Author.FirstName, Author.LastName

#● Get all reviews for a paper by the paper’s Id, where the paper was recommended to be published. The query should return the following data (columns): All columns from the Review table.

#● Get a count of all papers submitted.

#● Create a new paper submission. Remember this includes creating new records in boththe Author and Paper tables.

#● Try and Delete the first “Author” row in your Author table by the author’s id. Did you receive an error? 
If yes, print to the console the error you received. Also note in your message why the query failed. 
If it didn’t fail, print a message explaining why you were able to delete the row.

#I use several sql String inside java file to make it.
      /**
       * Question1: Get paper and author details by author ID (emailaddr)
       */

      sql1 = "SELECT * FROM AUTHOR,AUTHOR_SUBMITS_PAPER,PAPER "
          + "where author.EmailAddr=AUTHOR_SUBMITS_PAPER.EmailAddr_AUTHOR "
          + "and AUTHOR_SUBMITS_PAPER.ID_PAPER=PAPER.ID "
          + "and author.emailaddr = ?";

      /**
       * Question 2: Get reviews for a paper by paperId
       */

      sql2 = "SELECT * FROM REVIEW, REVIEWER_SUBMITS_REVIEW, REVIEWER_ASSIGNED_PAPER, REVIEWER, PAPER "
          + "where REVIEW.Id=REVIEWER_SUBMITS_REVIEW.ID_REVIEW "
          + "and REVIEWER_SUBMITS_REVIEW.EmailAddr_REVIEWER=REVIEWER.EmailAddr "
          + "and REVIEWER.EmailAddr=REVIEWER_ASSIGNED_PAPER.EmailAddr_REVIEWER "
          + "and PAPER.ID=REVIEWER_ASSIGNED_PAPER.ID_PAPER "
          + "and PAPER.ID = ?";

      /**
       * Question 3: Get a count of all paper submitted
       */

      sql3 = "SELECT COUNT(*) as numPapers from Paper";

      /**
       * Question 4: Submit a new Paper
       */

      sql4 = "insert into author values('Gyoung@pace.edu', 'Tom', 'James')";
      sql5 = "insert into paper values(6, 'Javaweb', 'learning', 'learningJava')";
      sql6 = "insert into AUTHOR_SUBMITS_PAPER values('Gyoung1@pace.edu', 6)";

      /**
       * Question 5: Deleting an author from the table
       */

      sql7 = "delete from author where EmailAddr = 'James456@pace.edu'";
        
