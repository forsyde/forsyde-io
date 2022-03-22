#ifndef LIBBUFFER_
#define LIBBUFFER_

#define DECLARE_CIRCULAR_BUFFER(buffer_name, token_type, max_size) typedef struct {token_type[max_size]; size_t front; size_t rear; size_t max}



#endif // LIBBUFFER_
