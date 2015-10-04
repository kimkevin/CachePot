# KimchiDataCache

A basic sample which shows how to transfer object of model between fragments simply without Pacelable and Serializable.

# Usage
You can copy only KimchiDataCache file to your util folder.

First put your data object to KimchiDataCache instance in your activity or fragment.

```java
User userItem = new User(1, "KimKevin");  // Sample Model

KimchiDataCache.getInstance().put(userItem);

// add your activity or fragment
```

Get your data object in your activity of fragment that you added.

```java
public class MainFragment extends Fragment{
    private User userItem;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userItem = KimchiDataCache.getInstance().get(User.class);
    }
```

# Developed By
* Kevin Yongjun Kim - imkimkevin@gmail.com

# Contributing
All contributions are welcome. Open a [Pull Requests](https://github.com/kimkevin/AndroidDataCache/pulls) or refer to
the [Issues](https://github.com/kimkevin/AndroidDataCache/issues) section.

# License
    Copyright 2015 William Mora

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
