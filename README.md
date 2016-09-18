# word2vecExample

This is an example application of word2vec, i.e. a deep learning network. 
It 'reads' the (public domain) text of Jane Austen's Pride and Prejudice and derives positions of terms in a high-dimensional space.

Learing and evaluation of the results are encapsulated in JUnit tests.Execution time can be expected to be roughly one minute.

As a result, we can derive associations of the most directly related terms based only on the lingual information derived from the novel.

This works surprisingly well.

```
Similar words to 'pride' : [disappoint, regret, company, none, connections]
Similar words to 'sister' : [mother, aunt, father, ladyship, daughter]
Similar words to 'married' : [thankful, comfortable, fellow, sincere, wherever]
Similar words to 'mind' : [daughters, temper, indifferent, favour, heart]
Similar words to 'laugh' : [so?, thankful, smil, unlucky, governess]
Similar words to 'friendship' : [obligation, aris, action, universal, establish]
Similar words to 'darcy' : [collins, wickham, bingley, gardiner, collin]
Similar words to 'coffee' : [whist, dish, anticipat, solemn, rous]
Similar words to 'room' : [drawing-room, hour, spent, ros, fami]
Similar words to 'flirt' : [unconcern, twelvemonth, aspect, certainty, formed]
Similar words to 'sleep' : [polite, weeks, tranquillity, writing, requir]
Similar words to 'you' : [u, myself, me, am, we]
Similar words to 'man' : [woman, circumstance, person, different, thousand]
Similar words to 'park' : [across, wood, parsonage, river, lane]
Tests run: 2, Failures: 0, Errors: 0, Skipped: 1, Time elapsed: 0.827 sec
```

