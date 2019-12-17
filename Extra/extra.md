## 解説し忘れた用語解説
```java
    public class A{
        // ここに書く変数は「フィールド」
        int a;

        // コンストラクタ
        // クラス名と同名でなければならない
        A(){
            // メソッド内に書く変数は「ローカル変数」
            int a;
        }
    }
```

フィールドとはクラス自体が持っている情報（変数）のことです。簡単に言えば、クラス内にあってメソッドの外に書いてある変数のことです。上のclassをインスタンス化した場合、インスタンスごとに異なるデータが保存されます。

# class
色々解説するにはまず、classについてから話始めなければいけません。

## 静的メンバー
### 静的フィールド

```java
public class Person{
    int HP;
    String  sex;
    ...
}
```

仮にこのPersonのインスタンス全てのHPに同じ値を入れたい場合には、静的フィールドを使いましょう。
`static`キーワードを使うことで共通の値を保持させることができます。

```java
    static int HP;
```

このHPにアクセスする場合、インスタンス化した名前を使わなくてもアクセスすることができます（上の場合Person.HP）。
インスタンス毎に変える必要がない、定数などで静的フィールドはよく使われます。例えばJavaではMath.PIがあります。

### 静的メソッド
メソッドもstaticをつけることで静的にすることができます。

```java
public class Person{
    
    static int getAge(){
        return age;
    }
    ...
}
```

静的フィールドと同様にインスタンス化した名前を使わなくても使用することができます。（上の場合Person.getAge()）。

例えばJavaではMath.log()があります。

## アクセス制御
他のクラスからアクセスを制御したい時にはアクセス修飾子を使います。フィールドとメソッドにつけられる修飾子は以下の4つです。

[![Image from Gyazo](https://i.gyazo.com/cb4b8f0ffc5cee9f2802b9870179eac3.jpg)](https://gyazo.com/cb4b8f0ffc5cee9f2802b9870179eac3)

また、classにつけられるアクセス修飾子は以下の2つです。

[![Image from Gyazo](https://i.gyazo.com/918ff938a3bada1a6b0b1cad3ebe7cb8.jpg)](https://gyazo.com/918ff938a3bada1a6b0b1cad3ebe7cb8)

publicにしていないclassは他のclassからは存在そのものが隠されます。インスタンス化することもできませんし、静的メンバーを利用することもできません。

[![Image from Gyazo](https://i.gyazo.com/33f6d3019be3a240c7a7b6d39b1e2274.jpg)](https://gyazo.com/33f6d3019be3a240c7a7b6d39b1e2274)


```java
public class Person{

    private int HP;
    private String sex;
    private String name;
    
    Person(String name){
        // 左辺のthis.nameはフィールドのname
        // 右辺のnameはメソッドの引数のname
        this.name = name;
    }

    public void showName(){
        System.out.println(name);
    }

    // getter
    // 値を読み込むためのメソッド
    public String getName(){
        return name;
    }

    // setter
    // 値を書き込むためのメソッド
    public void setName(String name){
        this.name = name;
    }
}
```

基本的には
- フィールドは全てprivate
- getter/setterはすべてpublic
- 通常のメソッドは基本public

という風にします（まあ好みです）。

## 継承
実はAndroidのコードを書いてる間はこれを使っていました。では見ていきましょう。

### 継承とは
似たような機能を持つclassを作るときに別々のclassとして実装しようとすると、全く同じメソッドやフィールドが存在していて書くのがだるくなってきます。そこで共通するフィールドやメソッドを記述して新しいclassを作ることができます。

例えば、AndroidではアプリのActivityを記述するひな形のclassとしてAppCombatActivityというものを使っています。実際に見てみましょう。

```java
// 1
public class MainActivity extends AppCompatActivity {

    // 2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 3
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    // 4
}
```

## 継承の使い方と注意

ここで行っていることを順に説明します。

1. classの継承元を**スーパークラス**、継承先を**サブクラス**といいます。サブクラスは複数存在できますが、スーパークラスは一つしか存在できません。サブクラスを作る際にはクラス名の横に**extends スーパークラス名**という風に記述します。
   
   上の場合だとMainActivityがAppCombatActivityを継承しているので、MainActivityというclassの中ではAppCombatActivityクラスのフィールド、メソッドを使用することができるようになります。

2. スーパークラスで定義したメソッドの内容を上書きしたいときにはメソッドの前に`@override`を付けます。このような機能をオーバーライドと言います。また`@`から始まり、class名やmethod名などの前に付与する要素を**アノテーション**といいます。ただし、作成したメソッドの引数はスーパークラスと同じ順番・数にしなければいけないことに注意してください。
   
   また次の説明のように、overrideされ上書きされたメソッドの処理内容は消える訳ではありません。

3. スーパークラスのメソッド、フィールドを使用したいときには**super.**をつけることで使用することができます。

    super.メソッド名();  
    super.フィールド名;
   
   上の場合だと、スーパークラスの同名のメソッドを使用した後に自分のしたい処理を記述していますね。

4. サブクラスのインスタンスにはスーパークラスのメソッド・フィールドが内包されています。簡単に言えば、サブクラスで記述しなくてもスーパークラスのメソッドやフィールドは勝手に動いています。
   
   例えば、戻るボタンを押したときの挙動を記述するonBackPressed()というメソッドはMainActivityで書いていなくても押したときに反応します。

[![Image from Gyazo](https://i.gyazo.com/76448f3f90c63f5f163eb45fc6c41725.jpg)](https://gyazo.com/76448f3f90c63f5f163eb45fc6c41725)

また、継承の原則として、**サブクラスになればなるほど具体的に、スーパークラスであればあるほど抽象的になるように記述するようにしましょう。**

見たいclassにカーソルを当ててCtrl+Bを押すことでそのclassの実装を見ることができます(Macの場合は⌘+B)。遡ってみたりして、あそんでみてください。

## 継承とオーバーライドの禁止
**final修飾子**は、それ自体が最後であることを示します。

- final変数  
  再代入できない変数として使います。


- finalフィールド  
  初期値のまま変更できないフィールドになります。要するに定数を定義できます。


- finalメソッド  
  サブクラスでオーバーライド出来なくなります。


- finalクラス  
  そのクラスを継承することができなくなります。

## 継承の応用
参考になる資料のpdfをつけておいたので知りたい方は読んでみてください。（著作権とかいろいろあるので他の人には回さないでください。下の参考文献の六章の内容です）
（すみませんがインターフェイスについての解説を書く暇がなかったので、インターフェイスについては資料の方での確認をお願いします。）

# 無名クラス
## 無名クラスとは
ButtonのsetOnClickListenerの構文に驚かれた方も少なくないと思います。これは**無名(匿名)クラス**というものです。無名クラスはjavaにおける特殊なクラスの一つで、クラス名がなく、あるメソッドの中で宣言され、そこでしか使われないという特徴があります。

大抵、抽象化されたclassやinterfaceを一度だけ具体的に使いたいときに使います。以下のように書くことでその場限りで動作するメソッドのようにふるまいます。

```java
    new 親クラスまたはインターフェイス名() {
        // 匿名クラスの内容 (フィールドやメソッドの定義)
    };
```


それでは実際にButtonのsetOnClickListenerを見てみましょう。

```java

        Button button = findViewById(R.id.button);
        // 1
        button.setOnClickListener(
            // 2 (これが無名クラス)
            new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // 中身
            }

        });
```

1. setOnClickListener()というメソッドの中身を簡単に言うと、Buttonがクリックされていた場合に引数のOnClickListenerというinterfaceを実行するというものです（そのままですね）。
   
2. 下に書いたのがViewクラスの中のOnClickListenerの実装です。このinterfaceは中のonClick()というメソッドを実行していたというわけです。上のコードでは、OnClickListenerを継承し、overrideしたメソッドを記述していたわけです。
   
   
そして、1と2の合わせ技で、onClick()の中に書いてある処理がButtonをクリックしたときに実行されていたというわけです。


```java
    /**
     * Interface definition for a callback to be invoked when a view is clicked.
     */
    public interface OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        void onClick(View v);
    }
```

## 無名クラス内にローカル変数を渡すとき  

OnClickListener内にローカル変数の値を渡したいときにはfinal修飾子をつけます（他にもあるにはあります）。

これはなぜかというと、OnClickListenerのインスタンスのライフタイム（消えるまでの時間）と、OnClickListenerを宣言するメソッドのライフタイムだと、OnClickListenerのインスタンスのライフタイムが長いことがあるからです。

OnClickListenerを宣言するメソッドのライフタイムが過ぎた時にそのメソッド内のローカル変数もなくなってしまいます。その場合、OnClickListenerで使用していた変数はなくなってしまいます。これでは困るため、OnClickListenerのインスタンス生成時に変数をコピーして無名クラス内に渡します。

しかし、javaにはCのようなポインタの機能はない為、ローカル変数が変更された場合にコピー先には変更が反映されません。これでは矛盾が起こってしまいます。これを防ぐためにコピーした後にローカル変数の値を変えられないという保証が必要になります。そこで、final修飾子をつけることで値を渡すことができます。

```java
        final String s = "Wataten";
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // sを使った処理可能
            }

        }
        );
```


# 参考文献
- 「新入社員のためのJAVAプログラミングテキスト　オブジェクト指向編」
