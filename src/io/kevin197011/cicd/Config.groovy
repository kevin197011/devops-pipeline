package io.kevin197011.cicd

class Config {
    // config project name
    static ArrayList<String> project = ['Aproject', 'Bproject', 'Cproject']

    // config project apps
    static LinkedHashMap<String, ArrayList<String>> appName = [
            'Aproject': ['a1', 'b1', 'c1', 'd1'],
            'Bproject': ['a2', 'b2', 'c2', 'd2'],
            'Cproject': ['a3', 'b4', 'c4', 'd4']
    ]

    // config app config path
    static LinkedHashMap<String, LinkedHashMap<String, String>> appPath = [
            'Aproject': [
                    'a1': '/configPath',
                    'b1': '/configPath',
                    'c1': '/configPath',
                    'd1': '/configPath'
            ],
            'Bproject': [
                    'a2': '/configPath',
                    'b2': '/configPath',
                    'c2': '/configPath',
                    'd2': '/configPath'
            ],
            'Cproject': [
                    'a3': '/configPath',
                    'b4': '/configPath',
                    'c4': '/configPath',
                    'd4': '/configPath'
            ]
    ]

    // config app config file
    static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> appConfig = [
            'Aproject': [
                    'a1': ['a.config', 'b.config'],
                    'b1': ['a.config', 'b.config'],
                    'c1': ['a.config', 'b.config'],
                    'd1': ['a.config', 'b.config']
            ],
            'Bproject': [
                    'a2': ['a.config', 'b.config'],
                    'b2': ['a.config', 'b.config'],
                    'c2': ['a.config', 'b.config'],
                    'd2': ['a.config', 'b.config']
            ],
            'Cproject': [
                    'a3': ['a.config', 'b.config'],
                    'b3': ['a.config', 'b.config'],
                    'c4': ['a.config', 'b.config'],
                    'd5': ['a.config', 'b.config']
            ]
    ]
}
