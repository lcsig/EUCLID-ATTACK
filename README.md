# EUCLID-ATTACK
An implementation of EuclidAttack. Which is An application of Euclidean algorithm in cryptanalysis of RSA.

It computes the factorization of n in deterministic time O((log n)<sup>2</sup>) bit operations. In the case where the public exponent (e) has the same order of magnitude as (n) and one of the integers k = (ed − 1)/&#981;(n) and (e − k) is < n<sup>0.25</sup>. 

## Theorem 
1. Let p and q be two odd primes of the same bit-size L and n = pq.
2. Consider positive integers e, d with 1 < e, d < &#981;(n) such that ed &#8801; 1 (mod &#981;(n)). Then (n, e) is the public key and d the private key for an RSA cryptosystem.
3. Set a = (n + 1) mod e and &#916; = gcd(e, a).
4. The extended Euclidean algorithm for e and a gives integers q<sub>i</sub> > 0 (i = 1, . . . , m) and r<sub>i</sub> (i = 0, . . . , m + 1) such that
    1. r<sub>0</sub> = e
    2. r<sub>1</sub> = a
    3. r<sub>m</sub> = &#916;
    4. r<sub>m+1</sub> = 0
    5. and r<sub>i - 1</sub> = r<sub>i</sub>*q<sub>i</sub> + r<sub>i + 1</sub>, 0 < r<sub>i + 1</sub> < r<sub>i</sub>.
5. Further, there are integers s<sub>i</sub>, t<sub>i</sub> with |t<sub>i</sub>| < e/r<sub>i - 1</sub> and |s<sub>i</sub>| < a/r<sub>i - 1</sub> satisfying
      s<sub>i</sub>*e + a*t<sub>i</sub> = r<sub>i</sub> , (i = 2, . . . , m + 1). 
6. Set &#956;<sub>i</sub> = gcd(t<sub>i</sub>, r<sub>i</sub>) and t'<sub>i</sub> = t<sub>i</sub>/&#956;<sub>i</sub> (i = 0, . . . , m + 1).

7. Let e > n/c, where c is an integer &#8805; 1, and k = (ed−1)/&#981;(n). 
8. Suppose that k or e−k is ≤ e<sup>0.25</sup>/6c<sup>0.5</sup>. Then, we have
    1. &#916; < e<sup>3/4</sup>.
    2. k = |t'<sub>j</sub>| and p+q = (a +|t'<sub>j</sub>|<sup>-1</sup>) mod e, where j is such that r<sub>j</sub> is the largest remainder < e<sup>3/4</sup>.
    3. Or k = e − |t'<sub>j</sub>| and p + q = (a + (e − |t'<sub>j</sub>|) −1 ) mod e, where j is such that r<sub>j</sub> is the largest remainder < e<sup>3/4</sup>.

## Algorithm 
Input: An RSA public key (n, e) with e > n/c (Read the paper for more info about c). 
Output: The primes p and q.
1. Compute a = (n + 1) mod e.
2. Using the extended Euclidean algorithm for e and a, compute the biggest remainder r<sub>j</sub> among them which are < e<sup>3/4</sup> and the associated integers s<sub>j</sub> , t<sub>j</sub> such that s<sub>j</sub>*e + a*t<sub>j</sub> = r<sub>j</sub>.
3. Compute &#956;<sub>j</sub> = gcd(t<sub>j</sub> , r<sub>j</sub>) and next t'<sub>j</sub> = t<sub>j</sub>/&#956;<sub>j</sub>.
4. Compute B<sub>1</sub> = (a + |t'<sub>j</sub>|<sup>-1</sup>) mod e and next the solutions u<sub>1</sub> and v<sub>1</sub> of equation [X<sup>2</sup> + B<sub>1</sub>X + n = 0]. If u<sub>1</sub> and v<sub>1</sub> are positive integers, then output (u<sub>1</sub> , v<sub>1</sub>). Otherwise, go to the next step.
5. Compute B<sub>2</sub> = (a + (e − |t'<sub>j</sub>|)<sup>-1</sup>) mod e and next the solutions u<sub>2</sub> and v<sub>2</sub> of equation [X<sup>2</sup> + B<sub>2</sub>X + n = 0]. If u<sub>2</sub> and v<sub>2</sub> are positive integers, then output (u<sub>2</sub> , v<sub>2</sub>). Otherwise, output FAIL.

## Paper
Poulakis, Dimitrios. (2020). An application of Euclidean algorithm in cryptanalysis of RSA. Elemente der Mathematik. 75. 114-120. 10.4171/EM/411. 
