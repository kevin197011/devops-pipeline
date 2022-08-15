package io.kevin197011.cicd

class Config {
    // project name
    final static ArrayList<String> projectName = ['YZ', 'XX', 'YY']

    // project gitlab
    final static LinkedHashMap<String, String> projectGit = [
            'YZ': 'https://gitlab.com/a1',
            'PYQ': 'https://gitlab.com/b1',
            'ZH': 'https://gitlab.com/c1'
    ]

    // project app
    final static LinkedHashMap<String, ArrayList<String>> appName = [
            'YZ': ['YZ_SIT_kllm', 'b1', 'c1', 'd1'],
            'XX': ['a2', 'b2', 'c2', 'd2'],
            'YY': ['a3', 'b4', 'c4', 'd4']
    ]

    // app host
    final static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> appHost = [
            'YZ': [
                    'a1': ['1.1.1.1', '2.2.2.2'],
                    'b1': ['1.1.1.1', '2.2.2.2'],
                    'c1': ['1.1.1.1', '2.2.2.2'],
                    'd1': ['1.1.1.1', '2.2.2.2']
            ],
            'PYQ': [
                    'a2': ['1.1.1.1', '2.2.2.2'],
                    'b2': ['1.1.1.1', '2.2.2.2'],
                    'c2': ['1.1.1.1', '2.2.2.2'],
                    'd2': ['1.1.1.1', '2.2.2.2']
            ],
            'ZH': [
                    'a3': ['1.1.1.1', '2.2.2.2'],
                    'b4': ['1.1.1.1', '2.2.2.2'],
                    'c4': ['1.1.1.1', '2.2.2.2'],
                    'd4': ['1.1.1.1', '2.2.2.2']
            ]
    ]

//    // app config path
//    final static LinkedHashMap<String, LinkedHashMap<String, String>> appPath = [
//            'YZ': [
//                    'a1': '/configPath',
//                    'b1': '/configPath',
//                    'c1': '/configPath',
//                    'd1': '/configPath'
//            ],
//            'PYQ': [
//                    'a2': '/configPath',
//                    'b2': '/configPath',
//                    'c2': '/configPath',
//                    'd2': '/configPath'
//            ],
//            'ZH': [
//                    'a3': '/configPath',
//                    'b4': '/configPath',
//                    'c4': '/configPath',
//                    'd4': '/configPath'
//            ]
//    ]

//    // app config file
//    final static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> appConfig = [
//            'YZ': [
//                    'a1': ['a.config', 'b.config'],
//                    'b1': ['a.config', 'b.config'],
//                    'c1': ['a.config', 'b.config'],
//                    'd1': ['a.config', 'b.config']
//            ],
//            'PYQ': [
//                    'a2': ['a.config', 'b.config'],
//                    'b2': ['a.config', 'b.config'],
//                    'c2': ['a.config', 'b.config'],
//                    'd2': ['a.config', 'b.config']
//            ],
//            'ZH': [
//                    'a3': ['a.config', 'b.config'],
//                    'b3': ['a.config', 'b.config'],
//                    'c4': ['a.config', 'b.config'],
//                    'd5': ['a.config', 'b.config']
//            ]
//    ]


}
