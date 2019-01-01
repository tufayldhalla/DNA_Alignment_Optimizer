The purpose of this project is to optimize the alignment of two DNA strands. In this way, one can compare the similarities between the two strands. The above problem can cover a wide extend of possible ways to compare the two strands, nonetheless, we will only focus on optimizing the alignment of the two strands by adding spaces in both DNA sequences. Furthermore, it is important to follow the rules identified below to conserve the DNA sequences’ validity and to provide a meaningful result.

1) the order of nucleotides in each sequence is conserved (i.e the genetic code is conserved).
2) No nucleotides can be added or removed
3) number of matches is maximized
4) number of gaps is minimized
5) number of mismatches is minimized

To calculate the optimal alignment, we use the Needleman-Wunsch Algorithm. This algorithm consists on comparing the two DNA strings by putting them as the header of a 2x2 matrix, and then calculating the cell of each position in the matrix.
