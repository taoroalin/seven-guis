(ns seven-guis.examples)
(def example-csv-conv
  (apply str
         (interpose "\n"
                    (map #(apply str % ","
                                 (interpose "," (for [x (range 97 (+ 97 25))] (str "=" "(" (char x) % "+" (char x) (inc %) "+" (char x) (+ 2 %) ")/3"))))
                         (range 99)))))
example-csv-conv
(def example-csv
  "Range,Pairs
   1,sum=A2+A3
   2,sum=A3+A4
   3,sum=A4+A5
   4,sum=A2+A4
   5,sum=A3+A5
   6,sum=A4+A6
   7,sum=A2+A5
   8,sum=A3+A6
   9,sum=A4+A7
   10,sum=A2+A6
   11,sum=A3+A7
   12,sum=A4+A8
   13,sum=A2+A7
   14,sum=A3+A8")

(def example-csv-fibonacci "Fibonacci
0
1
fib 1=B1+B2
fib 2=B2+B3
fib 3=B3+B4
fib 4=B4+B5
fib 5=B5+B6
fib 6=B6+B7
fib 7=B7+B8
fib 8=B8+B9
fib 9=B9+B10
fib 10=B10+B11
fib 11=B11+B12
fib 12=B12+B13
fib 13=B13+B14
fib 14=B14+B15
fib 15=B15+B16
fib 16=B16+B17
fib 17=B17+B18
fib 18=B18+B19
fib 19=B19+B20
fib 20=B20+B21
fib 21=B21+B22
fib 22=B22+B23
fib 23=B23+B24
fib 24=B24+B25
fib 25=B25+B26
fib 26=B26+B27
fib 27=B27+B28
fib 28=B28+B29
fib 29=B29+B30
fib 30=B30+B31
fib 31=B31+B32
fib 32=B32+B33
fib 33=B33+B34
fib 34=B34+B35
fib 35=B35+B36
fib 36=B36+B37
fib 37=B37+B38
fib 38=B38+B39
fib 39=B39+B40
fib 40=B40+B41
fib 41=B41+B42
fib 42=B42+B43
fib 43=B43+B44
fib 44=B44+B45
fib 45=B45+B46
fib 46=B46+B47
fib 47=B47+B48
fib 48=B48+B49
fib 49=B49+B50
fib 50=B50+B51
fib 51=B51+B52
fib 52=B52+B53
fib 53=B53+B54
fib 54=B54+B55
fib 55=B55+B56
fib 56=B56+B57
fib 57=B57+B58
fib 58=B58+B59
fib 59=B59+B60
fib 60=B60+B61
fib 61=B61+B62
fib 62=B62+B63
fib 63=B63+B64
fib 64=B64+B65
fib 65=B65+B66
fib 66=B66+B67
fib 67=B67+B68
fib 68=B68+B69
fib 69=B69+B70
fib 70=B70+B71
fib 71=B71+B72
fib 72=B72+B73
fib 73=B73+B74
fib 74=B74+B75
fib 75=B75+B76
fib 76=B76+B77
fib 77=B77+B78
fib 78=B78+B79
fib 79=B79+B80
fib 80=B80+B81
fib 81=B81+B82
fib 82=B82+B83
fib 83=B83+B84
fib 84=B84+B85
fib 85=B85+B86
fib 86=B86+B87
fib 87=B87+B88
fib 88=B88+B89
fib 89=B89+B90
fib 90=B90+B91
fib 91=B91+B92
fib 92=B92+B93
fib 93=B93+B94
fib 94=B94+B95
fib 95=B95+B96
fib 96=B96+B97")