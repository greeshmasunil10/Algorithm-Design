# Algorithm Designs

1. Socrates’ Cows
• Time Limit: 2 seconds
• Memory Limit: 256 Megabytes

Socrates’ cows are grazing in meadows. At each sunset Socrates blows a special whistle and the cows come
back to the stable. Socrates wants to know which cow(s) reaches the stable the fastest.
Each cow is in some meadow and some meadows might not have any cows. There are paths between
some pair of meadows (it is possible that two meadows have more than one path connecting them.) Starting
from any meadow a cow can reach the meadow in which the stable resides. Socrates’ cows are smart and they
always choose the shortest path to the stable.
All cows move with a same constant speed, and multiple cows can use a same path at the same time. The
meadows names are a small or capital English letter, i.e. a . . . z and A . . . Z . Before the whistle blows,
a meadow has a capital letter name if and only if a cow reside in it. The stable is in meadow z and there is
no cow in it before the whistle blows.
Input
• Line 1: Integer number P. (Number of paths that connect the meadows.)
• Lines 2 to P+1: Two letters and an integer number in each line, representing a path and the time it takes
the cows to pass it.
1 ≤ P ≤ 104 1 ≤ distance ≤ 103
Output
One line containing one letter and one integer number, representing any meadow whose cow(s) reaches the
stable the fastest (therefore definitely a capital letter) and the amount of time it take these cow(s) to reach the
stable.
Sample Test Cases
• Input
5
A d 9
B d 3
C e 9
d z 8
e z 3
• Output
B 11

2. Red
• Time Limit: 1.5 seconds
• Memory Limit: 512 Megabytes
Little red riding hood is a very competent graph theorist. She has n intervals [li, ri]. She created a n
vertex graph where each vertex represents an interval. There is an edge between u and v if and only if their
corresponding intervals (call them interval u and v) intersect, i.e. max(lv, lu) ≤ min(rv, ru). Now she want
you to help her to count the number of Dominating sets in this graph.
Set S of vertices of graph G is a Dominating set if and only if each vertex v of G is either a member of S
or one of its neighbor is in S.

Input
Line 1: Integer number n, number of intervals.
Lines 2 to n+1: li and ri the start and end point of interval i.
1 ≤ n ≤ 5000 0 ≤ li ≤ ri ≤ 10000
Output
One integer which is the number of Dominating sets of the graph modulo 109+7 (be careful about overflows).
Sample Test Cases
• Input 1 
2 
1 5
3 3
• Output 1 
3
• Input 1
3 
1 3
2 5
4 6
• Output 2 
5
 
3. Mr. Meeseeks’ Bakery
• Time Limit: 2 seconds
• Memory Limit: 256 Megabytes
In yet another dangerous adventure Morty joins his scientist grandfather Rick to buy a loaf of bread for
breakfast from one of the best bakeries in Faraway land. The residents of Faraway land are kind but impatient
creatures; if they get bored, they become merciless and will burn everything within their vicinity.
Mr. Meeseeks, an old friend of Rick and Morty, is the owner of the bakery. He is alone and has a lot
of customers. He is thrilled to see Rick and Morty, and he asks them for help. They divide the duties in the
following manner, Rick and Mr. Meesseks bake, and Morty will give the loaves of bread to customers.
As usual, there are n different queues in the bakery. Each customer wants one loaf of bread and will leave
after buying it. Morty can choose the front person of one queue and sell a loaf of bread to that person. No new
customer can enter the bakery.
Mr. Meeseeks and Rick together can bake a loaf of bread in a second. Customer j of queue i has the
patience level of pi,j (a positive integer number), if this customer does not receive a loaf of bread by this time
after they start baking, (s)he will burn the bakery.

If the bakery is on fire, Rick and Morty can escape using their teleport machine. Morty loves this work
and wants to do it as long as possible, until all customers are served or one of them set the place on fire. What
is the maximum number of customers Morty can sell a loaf of bread to?
Input
Line 1: Integer number n, number of queues.
Lines 2 to n+1: An integer number li, the number of customers in queue i. Followed by li integer numbers
pi,1, pi,2, . . . , pi,li , the patience level of ith queue customers.
1 ≤ n ≤
Σn
1
li ≤ 100 000 1 ≤ pi,j ≤ 109
Output
Maximum number of customers that Morty can sell a loaf of bread to.
Sample Test Cases
• Input 1
2
1 1
2 9 2
• Output 1
2
• Input 2
3
2 1 2
2 3 5
1 4
• Output 2
5
• Input 3
3
1 3
1 4
2 5 2
• Output 3
4

 
